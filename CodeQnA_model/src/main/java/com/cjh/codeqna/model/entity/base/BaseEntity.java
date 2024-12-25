package com.cjh.codeqna.model.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: cjh
 * @Description: 基本信息实体类
 * @Create: 2024-12-25 20:55
 */
@Data
@Schema(description = "基本信息实体类")
public class BaseEntity implements Serializable {
    @Schema(description = "唯一标识")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "是否删除")
    private Integer isDeleted;
}
