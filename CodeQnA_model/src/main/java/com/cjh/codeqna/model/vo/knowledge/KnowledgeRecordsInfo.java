package com.cjh.codeqna.model.vo.knowledge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 知识记录信息数据
 * @Create: 2025-04-08 21:54
 */
@Data
@Schema(description = "知识记录信息数据")
public class KnowledgeRecordsInfo {
    @Schema(description = "阅读数")
    private Long readCount;

    @Schema(description = "答复数")
    private Long responseCount;

    @Schema(description = "是否已赞赏")
    private Boolean isAppreciate;

    @Schema(description = "是否已关注")
    private Boolean isFollow;
}
