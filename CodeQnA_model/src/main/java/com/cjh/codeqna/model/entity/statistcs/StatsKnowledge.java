package com.cjh.codeqna.model.entity.statistcs;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: cjh
 * @Description: 知识统计实体类
 * @Create: 2025-02-23 23:09
 */
@Data
@Schema(description = "知识统计实体类")
public class StatsKnowledge extends BaseEntity {
    @Schema(description = "知识产出记录日期")
    private Date knowledgeDate;
    @Schema(description = "知识产出总数")
    private Long totalNum;
}
