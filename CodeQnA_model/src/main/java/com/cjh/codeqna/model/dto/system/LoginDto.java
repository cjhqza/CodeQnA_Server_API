package com.cjh.codeqna.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端登录数据传输对象
 * @Create: 2024-12-25 20:41
 */
@Data
@Schema(description = "管理员登录请求参数")
public class LoginDto {
    @Schema(description = "用户名")
    private String userName ;

    @Schema(description = "密码")
    private String password ;

    @Schema(description = "提交验证码")
    private String captcha ;

    @Schema(description = "验证码key")
    private String codeKey ;
}
