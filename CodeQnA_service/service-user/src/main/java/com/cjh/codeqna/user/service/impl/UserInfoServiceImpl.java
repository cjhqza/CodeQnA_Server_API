package com.cjh.codeqna.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSON;
import com.cjh.codeqna.common.exception.CodeQnAException;
import com.cjh.codeqna.feign.knowledge.KnowledgeFeignClient;
import com.cjh.codeqna.feign.tag.TagFeignClient;
import com.cjh.codeqna.model.dto.user.LoginCodeData;
import com.cjh.codeqna.model.dto.user.LoginPwData;
import com.cjh.codeqna.model.dto.user.UserDto;
import com.cjh.codeqna.model.dto.user.UserSearchDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.cjh.codeqna.model.vo.user.*;
import com.cjh.codeqna.user.mapper.UserInfoMapper;
import com.cjh.codeqna.user.service.UserInfoService;
import com.cjh.codeqna.util.AuthContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 用户信息服务接口实现类
 * @Create: 2025-04-10 0:45
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private TagFeignClient tagFeignClient;
    @Autowired
    private KnowledgeFeignClient knowledgeFeignClient;


    // 用户注册登录（验证码方式）
    @Override
    public UserBaseInfo codeLogin(LoginCodeData loginCodeData) {
        // 获取数据
        String phone = loginCodeData.getPhone();
        String code = loginCodeData.getCode();
        // 验证码校验
        String redisCode = redisTemplate.opsForValue().get("phone:code:" + phone);
        if (!StrUtil.equals(code, redisCode)) {
            throw new CodeQnAException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // 查看当前电话号码决定注册或登录
        DtUser dtUser = userInfoMapper.findByPhone(phone);
        if (dtUser != null) {
            System.out.println("---");
            // 存在该电话号码，判断当前账号状态
            if(dtUser.getStatus() == 0) {
                throw new CodeQnAException(ResultCodeEnum.ACCOUNT_STOP);
            }
        } else {
            // 否则，封装新数据
            dtUser = new DtUser();
            dtUser.setPhone(phone);
            dtUser.setUserName(phone);
            dtUser.setPassword(DigestUtils.md5DigestAsHex(phone.getBytes()));
            dtUser.setSex(0);
            dtUser.setHeadImgUrl("http://192.168.116.138:9001/codeqna-bucket/default/default_handsome.jpg");
            dtUser.setBgImgUrl("http://192.168.116.138:9001/codeqna-bucket/default/default_background.png");
            dtUser.setStatus(1);

            // 将新数据存入数据库中
            userInfoMapper.insert(dtUser);
        }

        System.out.println(dtUser.getId());

        // 删除redis中对应的验证码存储
        redisTemplate.delete("phone:code:" + phone);

        return packData(dtUser);
    }

    // 用户登录（密码方式）
    @Override
    public UserBaseInfo pwLogin(LoginPwData loginPwData) {
        // 获取数据
        String phone = loginPwData.getPhone();
        String password = loginPwData.getPassword();

        // 查询当前电话号码是否已有账号
        DtUser dtUser = userInfoMapper.findByPhone(phone);
        // 电话号码不存在
        if (dtUser == null) {
            throw new CodeQnAException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 电弧号码存在，密码不匹配
        if (!StrUtil.equals(DigestUtils.md5DigestAsHex(password.getBytes()), dtUser.getPassword())) {
            throw new CodeQnAException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 电话号码和密码匹配，但账号已被停用
        if (dtUser.getStatus() == 0) {
            throw new CodeQnAException(ResultCodeEnum.ACCOUNT_STOP);
        }

        // 一切正常，准许登录，返回数据
        return packData(dtUser);
    }

    // 用户退出
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login:" + token);
    }

    // 获取当前用户信息
    @Override
    public UserBaseInfo getUserInfo(String token) {
        return AuthContextUtil.getUserInfo();
    }

    // 远程调用：根据用户ID获取用户名
    @Override
    public DtUser getUserById(Long userId) {
        return userInfoMapper.getUserById(userId);
    }

    // 用户检索集合
    @Override
    public List<UserCbInfo> findByUserName(String userInput) {
        return userInfoMapper.findByUserName(userInput);
    }

    // 用户搜索结果集合
    @Override
    public PageInfo<UserSearchInfo> getUserSearchInfoList(UserSearchDto userSearchDto) {
        PageHelper.startPage(userSearchDto.getPageNum(), userSearchDto.getPageSize());
        List<UserSearchInfo> list = userInfoMapper.getUserSearchInfoList(userSearchDto.getUserName());
        return new PageInfo<>(list);
    }

    // 远程调用：根据用户ID获取用户名
    @Override
    public String getUserNameById(Long userId) {
        return userInfoMapper.getUserNameById(userId);
    }

    // 按关注数(关注高的)获取用户信息
    @Override
    public List<UserInfo> getUserInfoByFollow(Long userId) {
        return userInfoMapper.getUserInfoByFollow(userId);
    }

    // 根据用户标签查询用户信息
    @Override
    public List<UserInfo> getRelatedUserInfo() {
        // 获取用户信息
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        // 远程调用标签服务获取用户感兴趣标签集合
        List<Long> tagIds = tagFeignClient.getTagIdsByUserId(userInfo.getId());

        if (tagIds == null || tagIds.size() == 0) {
            return new ArrayList<>();
        }

        // 根据用户感兴趣标签集合获取用户信息集合
        return userInfoMapper.getRelatedUserInfo(tagIds, userInfo.getId());
    }

    // 获取用户详细信息
    @Override
    public UserDetialInfo getUserDetailInfo(Long userId) {
        // 根据userId找到用户搜索数据信息
        UserSearchInfo userSearchInfo = userInfoMapper.getUserSearchInfo(userId);
        // 根据userId找用户其他数据信息
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        UserotherInfo userotherInfo = userInfoMapper.getUserotherInfo(userId, userInfo == null ? 0L : userInfo.getId());
        // 远程调用知识服务根据用户id获取总的点赞数
        Long appreciateCount = knowledgeFeignClient.getAppreciateCountByUserId(userId);
        userotherInfo.setAppreciateCount(appreciateCount == null ? 0L : appreciateCount);
        // 远程调用标签服务根据用户id获取用户标签集合
        List<Long> tagIds = tagFeignClient.getTagIdsByUserId(userId);
        List<TagBaseInfo> tagBaseInfoList = tagFeignClient.getTagBaseInfoListByIds(tagIds);
        UserDetialInfo userDetialInfo = new UserDetialInfo();
        userDetialInfo.setUserSearchInfo(userSearchInfo);
        userDetialInfo.setUserotherInfo(userotherInfo);
        userDetialInfo.setTagBaseInfoList(tagBaseInfoList);

        return userDetialInfo;
    }

    // 用户关注功能实现
    @Override
    public void follow(Long userId) {
        // 判断当前用户是否已经关注过该用户
        UserBaseInfo userInfo = AuthContextUtil.getUserInfo();
        int count = userInfoMapper.getUserFollow(userInfo.getId(), userId);
        if (count > 0) {
            // 执行取消关注操作
            userInfoMapper.cancelFollow(userInfo.getId(), userId);
        } else {
            // 执行关注操作
            userInfoMapper.follow(userInfo.getId(), userId);
        }
    }

    // 修改用户信息
    @Override
    public void updateUserInfo(UserDto userDto) {
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        userInfoMapper.updateUserInfo(userDto);
    }

    @Override
    public Long validateToken(String token) {
        UserBaseInfo userBaseInfo = JSON.parseObject(redisTemplate.opsForValue().get("user:login:" + token), UserBaseInfo.class);
        if (userBaseInfo == null) {
            return null;
        }
        return userBaseInfo.getId();
    }

    private UserBaseInfo packData(DtUser dtUser) {
        // 生成用户唯一标识token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        // 封装返回数据
        UserBaseInfo userBaseInfo = new UserBaseInfo();
        userBaseInfo.setId(dtUser.getId());
        userBaseInfo.setUserName(dtUser.getUserName());
        userBaseInfo.setHeadImgUrl(dtUser.getHeadImgUrl());
        userBaseInfo.setToken(token);

        // token保存在redis中
            redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userBaseInfo), 3, TimeUnit.DAYS);

        return userBaseInfo;
    }
}
