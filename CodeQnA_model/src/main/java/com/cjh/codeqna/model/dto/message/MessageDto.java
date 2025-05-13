package com.cjh.codeqna.model.dto.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 消息传输数据对象
 * @Create: 2025-04-13 11:05
 */
@Data
public class MessageDto {
    @Schema(description = "会话id")
    private Long id;

    @Schema(description = "发送者id")
    private Long senderId;

    @Schema(description = "接收者id")
    private Long receiverId;

    @Schema(description = "消息内容")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发送时间")
    private String createTime;
}
