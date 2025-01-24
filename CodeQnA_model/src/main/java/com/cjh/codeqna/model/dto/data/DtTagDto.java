package com.cjh.codeqna.model.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端标签数据传输对象
 * @Create: 2025-01-16 22:20
 */
@Data
@Schema(description = "标签搜索请求参数")
public class DtTagDto {
    @Schema(description = "标签名称")
    private String tagName ;
}
