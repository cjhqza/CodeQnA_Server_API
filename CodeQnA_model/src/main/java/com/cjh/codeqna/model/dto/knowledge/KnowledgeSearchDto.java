package com.cjh.codeqna.model.dto.knowledge;

import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识搜索传递数据对象
 * @Create: 2025-04-10 15:19
 */
@Data
@Schema(description = "知识搜索传递数据对象")
public class KnowledgeSearchDto {
    @Schema(description = "输入内容")
    private String input;

    @Schema(description = "标签基本数据对象集合")
    List<TagBaseInfo> tagBaseInfoList;

    @Schema(description = "类型（2：全部 0：问答贴 1：文章）")
    private Integer type;

    @Schema(description = "分类（latest、popular、quality、follow）")
    private String category;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "页大小")
    private Integer pageSize;
}
