package com.cjh.codeqna.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户自动填补输入框响应数据
 * @Create: 2025-04-10 21:41
 */
@Data
@Schema(description = "用户自动填补输入框响应数据")
public class UserCbInfo {
    @Schema(description = "用户名")
    private String userName;
}
