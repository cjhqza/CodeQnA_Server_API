package com.cjh.codeqna.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.manager.mapper.SysUserMapper;
import com.cjh.codeqna.manager.service.SysUserService;
import com.cjh.codeqna.model.dto.system.LoginDto;
import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 系统用户服务实现类
 * @Create: 2024-12-25 17:24
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 管理员用户登录
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 获取管理员用户输入的验证码以及实际的验证码
        // String captcha = loginDto.getCaptcha();
        // String codeKey = loginDto.getCodeKey();
        // 从redis中获取存储的验证码


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
}
