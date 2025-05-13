package com.cjh.codeqna.model.vo.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 会话信息响应数据对象
 * @Create: 2025-04-13 15:28
 */
@Data
@Schema(description = "会话信息响应数据对象")
public class ChatInfo {
    @Schema(description = "会话Id")
    private Long id;

    @Schema(description = "交流的用户头像")
    private String toHeadImgUrl;

    @Schema(description = "交流的用户id")
    private Long toUserId;

    @Schema(description = "交流的用户名")
    private String toUserName;
}
