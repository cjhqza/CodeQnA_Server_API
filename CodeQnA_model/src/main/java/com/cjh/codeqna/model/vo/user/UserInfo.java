package com.cjh.codeqna.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户信息响应数据对象
 * @Create: 2025-04-12 17:04
 */
@Data
@Schema(description = "用户信息响应数据对象")
public class UserInfo {
    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "用户头像")
    private String headImgUrl;

    @Schema(description = "是否已关注")
    private Boolean isFollow;

    @Schema(description = "关注数")
    private Long followCount;
}
