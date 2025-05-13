package com.cjh.codeqna.model.entity.data;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 答复评论数据对象
 * @Create: 2025-02-11 22:39
 */
@Data
@Schema(description = "答复评论数据对象")
public class DtComment extends BaseEntity {
    @Schema(description = "评论id")
    private Long id;

    @Schema(description = "知识id")
    private Long knowledgeId;

    @Schema(description = "父评论id")
    private Long parentId;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "评论内容")
    private String content;
}
