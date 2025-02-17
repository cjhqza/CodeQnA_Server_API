package com.cjh.codeqna.model.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端知识数据传输对象
 * @Create: 2025-01-25 23:31
 */
@Data
@Schema(description = "知识搜索请求参数")
public class DtKnowledgeDto {
    @Schema(description = "标题内容")
    private String title;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "标签id")
    private Long tagId;
}
