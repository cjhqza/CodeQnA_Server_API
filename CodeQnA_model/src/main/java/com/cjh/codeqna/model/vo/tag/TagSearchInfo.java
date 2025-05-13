package com.cjh.codeqna.model.vo.tag;

import com.cjh.codeqna.model.entity.data.DtTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 标签搜索响应数据对象
 * @Create: 2025-04-10 9:05
 */
@Data
@Schema(description = "标签搜索响应数据对象")
public class TagSearchInfo {
    @Schema(description = "标签信息")
    private DtTag dtTag;

    @Schema(description = "相关问答贴数量")
    private Long postNum;

    @Schema(description = "相关文章数量")
    private Long articleNum;

    @Schema(description = "是否已关注")
    private Boolean isFollow;
}
