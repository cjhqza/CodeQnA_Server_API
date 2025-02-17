package com.cjh.codeqna.model.entity.data;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 数据知识信息实体类
 * @Create: 2025-01-25 23:35
 */
@Data
@Schema(description = "数据知识信息实体类")
public class DtKnowledge extends BaseEntity {
    @Schema(description = "知识类型(0：问答贴 1：文章)")
    private int type;
    @Schema(description = "创作作者id")
    private Long userId;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "创作内容")
    private String content;
    @Schema(description = "审批状态（-1：审批不通过【默认不通过，此时认作为草稿】； 0：正在审批【不认作草稿】； 1：审批通过【正式上架】）")
    private int status;
}
