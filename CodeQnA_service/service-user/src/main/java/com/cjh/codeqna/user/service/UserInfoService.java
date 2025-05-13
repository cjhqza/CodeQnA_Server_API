package com.cjh.codeqna.user.service;

import cn.hutool.system.UserInfo;
import com.cjh.codeqna.model.dto.user.LoginCodeData;
import com.cjh.codeqna.model.dto.user.LoginPwData;
import com.cjh.codeqna.model.dto.user.UserDto;
import com.cjh.codeqna.model.dto.user.UserSearchDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.model.vo.user.UserCbInfo;
import com.cjh.codeqna.model.vo.user.UserDetialInfo;
import com.cjh.codeqna.model.vo.user.UserSearchInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户信息服务接口
 * @Create: 2025-04-10 0:45
 */
public interface UserInfoService {

    // 用户注册登录（验证码方式）
    UserBaseInfo codeLogin(LoginCodeData loginCodeData);

    // 用户登录（密码方式）
    UserBaseInfo pwLogin(LoginPwData loginPwData);

    // 用户退出
    void logout(String token);

    // 获取当前用户信息
    UserBaseInfo getUserInfo(String token);

    // 远程调用：根据用户ID获取用户对象
    DtUser getUserById(Long userId);

    // 用户检索集合
    List<UserCbInfo> findByUserName(String userInput);

    // 用户搜索结果集合
    PageInfo<UserSearchInfo> getUserSearchInfoList(UserSearchDto userSearchDto);

    // 远程调用：根据用户ID获取用户名
    String getUserNameById(Long userId);

    // 按关注数(关注高的)获取用户信息
    List<UserInfo> getUserInfoByFollow(Long userId);

    // 根据用户标签查询用户信息
    List<UserInfo> getRelatedUserInfo();

    // 获取用户详细信息
    UserDetialInfo getUserDetailInfo(Long userId);

    // 用户关注功能实现
    void follow(Long userId);

    // 修改用户信息
    void updateUserInfo(UserDto userDto);

    // 远程调用：验证token
    Long validateToken(String token);
}
