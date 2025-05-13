package com.cjh.codeqna.model.dto.approval;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 建议传递数据对象
 * @Create: 2025-04-13 0:50
 */
@Data
@Schema(description = "建议传递数据对象")
public class AprvSugDto {
    @Schema(description = "建议者id")
    private Long userId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "建议内容")
    private String content;
}
