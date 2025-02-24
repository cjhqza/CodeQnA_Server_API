package com.cjh.codeqna.model.vo.statistcs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识统计结果响应对象
 * @Create: 2025-02-24 10:36
 */
@Data
@Schema(description = "知识统计结果响应对象")
public class StatsKnowledgeVo {
    @Schema(description = "日期数据集合")
    private List<String> dateList ;

    @Schema(description = "总产量数据集合")
    private List<Long> numList ;
}
