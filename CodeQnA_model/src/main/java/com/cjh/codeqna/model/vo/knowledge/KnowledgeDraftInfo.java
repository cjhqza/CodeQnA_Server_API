package com.cjh.codeqna.model.vo.knowledge;

import com.cjh.codeqna.model.entity.data.DtKnowledge;
import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识草稿信息数据对象
 * @Create: 2025-04-11 10:26
 */
@Data
@Schema(description = "知识草稿信息数据对象")
public class KnowledgeDraftInfo {
    @Schema(description = "知识数据")
    private DtKnowledge dtKnowledge;

    @Schema(description = "相关标签集合")
    private List<TagBaseInfo> tagBaseInfoList;
}
