package com.cjh.codeqna.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户修改信息传递数据对象
 * @Create: 2025-04-13 2:37
 */
@Data
@Schema(description = "用户修改信息传递数据对象")
public class UserDto {
    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "头像")
    private String headImgUrl;

    @Schema(description = "背景图")
    private String bgImgUrl;

    @Schema(description = "签名")
    private String signture;

    @Schema(description = "自我介绍")
    private String selfIntro;
}
