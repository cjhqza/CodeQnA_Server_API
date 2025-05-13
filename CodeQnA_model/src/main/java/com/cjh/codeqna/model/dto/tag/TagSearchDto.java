package com.cjh.codeqna.model.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 标签搜索传输数据对象
 * @Create: 2025-04-10 8:59
 */
@Data
@Schema(description = "标签搜索信息数据对象")
public class TagSearchDto {
    @Schema(description = "输入内容")
    private String input;

    @Schema(description = "类别（热门hot/篇数多most）")
    private String category;

    @Schema(description = "页数")
    private Integer pageNum;

    @Schema(description = "页大小")
    private Integer pageSize;
}
