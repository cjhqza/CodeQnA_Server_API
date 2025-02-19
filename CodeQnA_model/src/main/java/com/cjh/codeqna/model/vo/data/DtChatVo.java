package com.cjh.codeqna.model.vo.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cjh
 * @Description: 数据聊天会话响应结果实体类
 * @Create: 2025-02-19 16:31
 */
@Data
public class DtChatVo {
    @Schema(description = "唯一编号")
    private Long id;
    @Schema(description = "用户1昵称")
    private String userName1;
    @Schema(description = "用户2昵称")
    private String userName2;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最近发送时间")
    private Date lastMessageTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "用户1的ID")
    private Long user1Id;
    @Schema(description = "用户2的ID")
    private Long user2Id;

}
