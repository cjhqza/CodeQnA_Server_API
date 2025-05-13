package com.cjh.codeqna.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户免密登录数据传输对象
 * @Create: 2025-04-10 15:29
 */
@Data
@Schema(description = "用户免密登录数据传输对象")
public class LoginCodeData {
    @Schema(description = "电话号码")
    private String phone;
    @Schema(description = "验证码")
    private String code;
}
