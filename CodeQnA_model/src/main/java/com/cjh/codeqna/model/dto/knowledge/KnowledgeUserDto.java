package com.cjh.codeqna.model.dto.knowledge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户空间知识传递数据对象
 * @Create: 2025-04-12 21:44
 */
@Data
@Schema(description = "用户空间知识传递数据对象")
public class KnowledgeUserDto {
    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "知识类型")
    private Integer type;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "页大小")
    private Integer pageSize;
}
