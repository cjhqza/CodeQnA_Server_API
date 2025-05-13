package com.cjh.codeqna.model.dto.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 举报信息传递数据对象
 * @Create: 2025-04-12 12:37
 */
@Data
@Schema(description = "举报信息传递数据对象")
public class ReportDto {
    @Schema(description = "举报类型")
    private Integer targetType;

    @Schema(description = "举报对象id")
    private Long targetId;

    @Schema(description = "举报者id")
    private Long reporterId;

    @Schema(description = "举报内容")
    private String reportReason;
}
