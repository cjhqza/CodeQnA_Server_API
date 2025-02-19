package com.cjh.codeqna.model.vo.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cjh
 * @Description: 数据评论响应结果实体类
 * @Create: 2025-02-17 17:27
 */
@Data
public class DtCommentVo {
    @Schema(description = "唯一编号")
    private Long id;
    @Schema(description = "知识ID")
    private Long knowledgeId;
    @Schema(description = "知识类型(0：问答贴 1：文章)")
    private Integer type;
    @Schema(description = "评论者名")
    private String userName;
    @Schema(description = "上级评论ID")
    private Long parentId;
    @Schema(description = "评论内容")
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;
}
