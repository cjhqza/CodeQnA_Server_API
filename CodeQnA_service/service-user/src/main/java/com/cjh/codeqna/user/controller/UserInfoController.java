package com.cjh.codeqna.user.controller;

import cn.hutool.system.UserInfo;
import com.cjh.codeqna.model.dto.user.LoginCodeData;
import com.cjh.codeqna.model.dto.user.LoginPwData;
import com.cjh.codeqna.model.dto.user.UserDto;
import com.cjh.codeqna.model.dto.user.UserSearchDto;
import com.cjh.codeqna.model.entity.data.DtUser;
import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;
import com.cjh.codeqna.model.vo.user.UserCbInfo;
import com.cjh.codeqna.model.vo.user.UserDetialInfo;
import com.cjh.codeqna.model.vo.user.UserSearchInfo;
import com.cjh.codeqna.user.service.UserInfoService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 用户信息控制层
 * @Create: 2025-04-10 0:44
 */
@RestController
@RequestMapping(value = "/api/user/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    // 用户注册登录（验证码方式）
    @PostMapping (value = "/codeLogin")
    public Result codeLogin(@RequestBody LoginCodeData loginCodeData) {
        System.out.println(loginCodeData);
        UserBaseInfo userBaseInfo = userInfoService.codeLogin(loginCodeData);
        return Result.build(userBaseInfo, ResultCodeEnum.SUCCESS);
    }

    // 用户登录（密码方式）
    @PostMapping (value = "/pwLogin")
    public Result pwLogin(@RequestBody LoginPwData loginPwData) {
        UserBaseInfo userBaseInfo = userInfoService.pwLogin(loginPwData);
        return Result.build(userBaseInfo, ResultCodeEnum.SUCCESS);
    }

    // 用户退出
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(name="token") String token) {
        // System.out.println(token);
        userInfoService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 获取当前用户信息
    @GetMapping(value = "/auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserBaseInfo userBaseInfo = userInfoService.getUserInfo(token);
        return Result.build(userBaseInfo, ResultCodeEnum.SUCCESS);
    }

    // 远程调用：根据用户ID获取用户对象
    @GetMapping(value = "/getUserById/{userId}")
    public DtUser getUserById(@PathVariable(value = "userId") Long userId) {
        return userInfoService.getUserById(userId);
    }

    // 远程调用：根据用户ID获取用户名
    @GetMapping(value = "/getUserNameById/{userId}")
    public String getUserNameById(@PathVariable(value = "userId") Long userId) {
        return userInfoService.getUserNameById(userId);
    }

    // 用户检索集合
    @GetMapping(value = "/findByUserName/{userInput}")
    public Result findByUserName(@PathVariable(value = "userInput") String userInput) {
        List<UserCbInfo> list = userInfoService.findByUserName(userInput);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


    // 用户搜索结果集合
    @PostMapping(value = "/getUserSearchInfoList")
    public Result getUserSearchInfoList(@RequestBody UserSearchDto userSearchDto) {
        PageInfo<UserSearchInfo> list = userInfoService.getUserSearchInfoList(userSearchDto);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 按关注数(关注高的)获取用户信息
    @GetMapping(value = "/getUserInfoByFollow/{userId}")
    public Result getUserInfoByFollow(@PathVariable(value = "userId") Long userId) {
        List<UserInfo> list = userInfoService.getUserInfoByFollow(userId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 根据用户标签查询用户信息
    @GetMapping(value = "/auth/getRelatedUserInfo")
    public Result getRelatedUserInfo() {
        List<UserInfo> list = userInfoService.getRelatedUserInfo();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    // 获取用户详细信息
    @GetMapping(value = "/auth/getUserDetailInfo/{userId}")
    public Result getUserDetailInfo(@PathVariable(value = "userId") Long userId) {
        UserDetialInfo userDetialInfo = userInfoService.getUserDetailInfo(userId);
        return Result.build(userDetialInfo, ResultCodeEnum.SUCCESS);
    }

    // 用户关注功能实现
    @PostMapping(value = "/auth/follow/{userId}")
    public Result follow(@PathVariable(value = "userId") Long userId) {
        userInfoService.follow(userId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 修改用户信息
    @PostMapping(value = "/auth/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserDto userDto) {
        userInfoService.updateUserInfo(userDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 远程调用：验证token
    @GetMapping(value = "/validateToken/{token}")
    public Long validateToken(@PathVariable(value = "token") String token) {
        return userInfoService.validateToken(token);
    }
}
