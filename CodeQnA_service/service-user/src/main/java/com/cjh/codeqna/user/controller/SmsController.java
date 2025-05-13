package com.cjh.codeqna.user.controller;

import com.cjh.codeqna.model.vo.common.Result;
import com.cjh.codeqna.model.vo.common.ResultCodeEnum;
import com.cjh.codeqna.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: cjh
 * @Description: 手机短信验证码控制器
 * @Create: 2025-04-09 22:00
 */
@RestController
@RequestMapping(value = "/api/user/sms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @GetMapping(value = "/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone) {
        // System.out.println(phone + "来获取短信验证码");
        smsService.sendCode(phone);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
