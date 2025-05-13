package com.cjh.codeqna.user.service.impl;

import com.cjh.codeqna.user.service.SmsService;
import com.cjh.codeqna.util.HttpUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 手机短信验证码服务接口实现类
 * @Create: 2025-04-09 22:04
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendCode(String phone) {
        String code = redisTemplate.opsForValue().get("phone:code:" + phone);
        if(StringUtils.hasText(code)) {
            return;
        }

        // 1 生成验证码
        code = RandomStringUtils.randomNumeric(6);
        // 2 把生成验证码放到redis里面，设置过期时间
        redisTemplate.opsForValue().set("phone:code:" + phone, code, 30, TimeUnit.MINUTES);
        // 3 向手机号发送短信验证码
        sendSms(phone , code) ;

    }

    // 发送短信验证码
    public void sendSms(String phone, String code) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "9bb8d0e32a804e4f8fccf8a75dea0e2c";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + code);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
