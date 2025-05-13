package com.cjh.codeqna.model.vo.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 举报回执简单数据对象
 * @Create: 2025-04-13 0:16
 */
@Data
@Schema(description = "举报回执简单数据对象")
public class SimpleReportVo {
    @Schema(description = "唯一编号")
    private Long id;

    @Schema(description = "举报原因")
    private String reportReason;

    @Schema(description = "举报回信内容")
    private String feedback;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "举报回信时间")
    private String processTime;
}
