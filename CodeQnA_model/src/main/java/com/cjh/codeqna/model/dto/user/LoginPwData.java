package com.cjh.codeqna.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户密码登录数据传输对象
 * @Create: 2025-04-11 10:51
 */
@Data
@Schema(description = "用户密码登录数据传输对象")
public class LoginPwData {
    @Schema(description = "电话号码")
    private String phone;
    @Schema(description = "密码")
    private String password;
}
