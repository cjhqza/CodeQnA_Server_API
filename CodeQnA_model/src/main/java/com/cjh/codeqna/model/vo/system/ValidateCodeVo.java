package com.cjh.codeqna.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 验证码响应结果实体类
 * @Create: 2024-12-25 22:26
 */
@Data
public class ValidateCodeVo {
    @Schema(description = "验证码key")
    private String codeKey ;

    @Schema(description = "验证码value")
    private String codeValue ;
}
