package com.cjh.codeqna.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 提交评论传输数据对象
 * @Create: 2025-04-12 13:28
 */
@Data
@Schema(description = "提交评论传递数据对象")
public class CommentDto {
    @Schema(description = "知识id")
    private Long knowledgeId;

    @Schema(description = "父评论id")
    private Long parentId;

    @Schema(description = "祖先评论id")
    private Long ancestorId;

    @Schema(description = "答复人id")
    private Long userId;

    @Schema(description = "评论内容")
    private String content;
}
