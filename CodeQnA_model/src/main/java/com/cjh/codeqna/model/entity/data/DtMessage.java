package com.cjh.codeqna.model.entity.data;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 数据聊天消息实体类
 * @Create: 2025-02-19 20:59
 */
@Data
@Schema(description = "数据聊天消息实体类")
public class DtMessage extends BaseEntity {
    @Schema(description = "唯一编号")
    private Long id;
    @Schema(description = "发送者ID")
    private Long senderId;
    @Schema(description = "会话ID")
    private Long chatId;
    @Schema(description = "消息内容")
    private String content;
}
