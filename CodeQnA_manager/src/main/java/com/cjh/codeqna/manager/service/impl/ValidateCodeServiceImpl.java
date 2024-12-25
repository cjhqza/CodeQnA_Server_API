package com.cjh.codeqna.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.cjh.codeqna.manager.service.ValidateCodeService;
import com.cjh.codeqna.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: cjh
 * @Description: 验证码服务接口实现类
 * @Create: 2024-12-25 22:30
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 生成图片验证码
    @Override
    public ValidateCodeVo generateValidateCode() {
        // 通过hutool工具生成图片验证码
        // 宽 高 验证码个数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        // 获取验证码的值
        String codeValue = circleCaptcha.getCode();
        // 获取图片验证码，base64编码
        String imageBase64 = circleCaptcha.getImageBase64();
        // 将验证码存储到redis里
        String codeKey = UUID.randomUUID().toString().replaceAll("-", "");
        // 设置验证码3分钟后过期
        redisTemplate.opsForValue().set("manager-user:validate" + codeKey, codeValue, 3, TimeUnit.MINUTES);
        // 返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        return validateCodeVo;
    }
}
