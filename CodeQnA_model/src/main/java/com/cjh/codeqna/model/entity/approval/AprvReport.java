package com.cjh.codeqna.model.entity.approval;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cjh
 * @Description: 举报审批实体类
 * @Create: 2025-02-22 12:34
 */
@Data
public class AprvReport {
    @Schema(description = "唯一编号")
    private Long id;
    @Schema(description = "举报对象类型（0：用户类 1：知识类 2：评论类）")
    private Integer targetType;
    @Schema(description = "举报对象id")
    private Long targetId;
    @Schema(description = "举报人id")
    private Long reporter_id;
    @Schema(description = "举报原因描述")
    private String reportReason;
    @Schema(description = "举报状态（-1：已拒绝 0：待处理 1：已处理）")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "举报时间")
    private Date reportTime;

    @Schema(description = "举报者名")
    private String reporter_userName;

}
