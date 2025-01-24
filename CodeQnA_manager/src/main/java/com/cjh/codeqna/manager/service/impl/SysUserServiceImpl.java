package com.cjh.codeqna.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.SysRoleUserMapper;
import com.cjh.codeqna.manager.mapper.SysUserMapper;
import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.model.dto.system.AssignRoleDto;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.dto.system.SysUserDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.LoginVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 系统用户服务接口实现类
 * @Create: 2024-12-25 17:24
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 管理员用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 获取管理员用户输入的验证码以及存储在redis中的codeKey
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        // 根据获得的codeKey从redis中查询获取存储的验证码
        String codeValue = redisTemplate.opsForValue().get("manager-user:validate" + codeKey);
        // 校验输入的验证码和实际的验证码，不忽略大小写
        // 如果不一致
        if (StrUtil.isEmpty(codeValue) || !StrUtil.equals(codeValue, captcha)) {
            throw new CodeQnAException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // 如果一致，可删除redis对应存储的验证码
        redisTemplate.delete("manager-user:validate" + codeKey);
        // 获取输入的管理员用户名
        String userName = loginDto.getUserName();
        // 根据管理员用户名从系统管理员用户表里查询获取得到其实体类
        SysUser sysUser = sysUserMapper.findUserByUsername(userName);
        // 如果管理员用户不存在，返回错误信息
        if (sysUser == null) {
            throw new CodeQnAException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 如果管理员用户存在，则匹配密码
        // 获取实际的密码（已经过md5加密）
        String db_password = sysUser.getPassword();
        // 将输入密码进行md5加密
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        // 如果密码不一致，则返回错误信息，登录失败
        if (!input_password.equals(db_password)) {
            throw new CodeQnAException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 如果密码一致，则生成管理员用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 将登录成功的管理员用户信息存储到redis里面
        // key：前缀（如果有的话） + token
        // value：管理员用户信息
        // 当前设置了此数据可保存3天，3天过后将被删除
        redisTemplate.opsForValue().set("manager-user:login" + token, JSON.toJSONString(sysUser), 3, TimeUnit.DAYS);
        // 设置和返回登录响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    // 根据token查询redis获取管理员用户信息
    @Override
    public SysUser getSysUserInfo(String token) {
        // 注意获取的value此前是以JSON格式存储，需要进行转换
        String sysUserJSON = redisTemplate.opsForValue().get("manager-user:login" + token);
        return JSON.parseObject(sysUserJSON, SysUser.class);
    }

    // 管理员用户退出
    @Override
    public void logout(String token) {
        redisTemplate.delete("manager-user:login" + token);
    }

    // 管理员列表
    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        return new PageInfo<>(list);
    }

    // 人员添加
    @Override
    public void addSysUser(SysUser sysUser) {
        // 判断人员名称userName不能重复，只有name可以
        SysUser dbSysUser = sysUserMapper.findUserByUsername(sysUser.getUserName());
        // 重复则需要抛出异常
        if (dbSysUser != null) {
            throw new CodeQnAException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        // 将密码进行加密并保存
        String md5pw = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(md5pw);
        // 默认设置status为1：正常
        sysUser.setStatus(1);
        // 检查是否有头像地址，没有则设置默认头像地址
        if (StrUtil.isEmpty(sysUser.getAvatar())) {
            sysUser.setAvatar("http://192.168.116.129:9001/codeqna-bucket/default/default_handsome.jpg");
        }
        // 将人员信息保存
        sysUserMapper.add(sysUser);
    }

    // 人员修改
    @Override
    public void editSysUser(SysUser sysUser) {
        sysUserMapper.edit(sysUser);
    }

    // 人员删除
    @Override
    public void deleteSysUserById(Long sysUserId) {
        sysUserMapper.delete(sysUserId);
    }

    // 人员分配角色
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        // 获取当前人员的id
        Long userId = assignRoleDto.getUserId();
        // 删除之前人员已分配到的角色
        sysRoleUserMapper.deleteByUserId(userId);
        // 再重新分配角色
        for (Long roleId : assignRoleDto.getRoleIdsList()) {
            sysRoleUserMapper.assign(userId, roleId);
        }

    }
}
