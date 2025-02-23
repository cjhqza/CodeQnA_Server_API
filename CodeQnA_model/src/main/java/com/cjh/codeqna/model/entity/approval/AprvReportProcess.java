package com.cjh.codeqna.model.entity.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cjh
 * @Description: 前端举报审批数据传递对象
 * @Create: 2025-02-23 10:35
 */
@Data
@Schema(description = "举报审批处理请求参数")
public class AprvReportProcess {
    @Schema(description = "唯一编号")
    private Long id;
    @Schema(description = "举报数据id")
    private Long reportId;
    @Schema(description = "处理管理员id")
    private Long processorId;
    @Schema(description = "对被举报者处理措施")
    private String actionToken;
    @Schema(description = "对举报者的反馈回应")
    private String feedback;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "处理时间")
    private Date processTime;
    @Schema(description = "是否删除")
    private Integer isDeleted;
}
