package com.cjh.codeqna.user.service;

/**
 * @Author: cjh
 * @Description: 手机短信验证码服务接口
 * @Create: 2025-04-09 22:03
 */
public interface SmsService {
    void sendCode(String phone);
}
