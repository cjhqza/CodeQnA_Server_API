package com.cjh.codeqna.model.dto.statistcs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 知识统计数据传递对象
 * @Create: 2025-02-24 10:31
 */
@Data
@Schema(description = "知识统计数据传递对象")
public class StatsKnowledgeDto {
    @Schema(description = "开始时间")
    private String beginCreateTime;
    @Schema(description = "结束时间")
    private String endCreateTime;
}
