package com.cjh.codeqna.util;

import com.cjh.codeqna.model.entity.system.SysUser;
import com.cjh.codeqna.model.vo.user.UserBaseInfo;

/**
 * @Author: cjh
 * @Description: 封装ThreadLocal对象类
 * @Create: 2024-12-26 15:22
 */
public class AuthContextUtil {
    // 创建ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();
    private static final ThreadLocal<UserBaseInfo> userInfoThreadLocal = new ThreadLocal<>();

    // 添加管理员用户数据
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }
    // 获取管理员用户数据
    public static SysUser get() {
        return threadLocal.get();
    }
    // 删除管理员用户数据
    public static void remove() {
        threadLocal.remove();
    }

    // 添加用户数据
    public static void setUserInfo(UserBaseInfo userBaseInfo) {
        userInfoThreadLocal.set(userBaseInfo);
    }

    // 获取用户数据
    public static UserBaseInfo getUserInfo() {
        return userInfoThreadLocal.get();
    }

    // 删除用户数据
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }

}
