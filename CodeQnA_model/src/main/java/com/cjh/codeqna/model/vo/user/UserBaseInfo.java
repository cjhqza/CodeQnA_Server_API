package com.cjh.codeqna.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户登录响应数据
 * @Create: 2025-04-10 15:31
 */
@Data
@Schema(description = "用户登录响应数据")
public class UserBaseInfo {
    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "头像")
    private String headImgUrl;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "唯一token")
    private String token;
}
