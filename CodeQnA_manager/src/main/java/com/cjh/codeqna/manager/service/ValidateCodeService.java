package com.cjh.codeqna.manager.service;

import com.cjh.codeqna.model.vo.system.ValidateCodeVo;

/**
 * @Author: cjh
 * @Description: 验证码服务类接口
 * @Create: 2024-12-25 22:29
 */
public interface ValidateCodeService {
    // 生成图片验证码
    ValidateCodeVo generateValidateCode();
}
