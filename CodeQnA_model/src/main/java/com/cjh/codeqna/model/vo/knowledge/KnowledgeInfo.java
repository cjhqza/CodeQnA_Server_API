package com.cjh.codeqna.model.vo.knowledge;

import com.cjh.codeqna.model.vo.tag.TagBaseInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: cjh
 * @Description: 知识信息数据
 * @Create: 2025-04-07 15:52
 */
@Data
@Schema(description = "知识信息数据")
public class KnowledgeInfo {
    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "知识类型(0：问答贴 1：文章)")
    private Integer type;

    @Schema(description = "创作者id")
    private Long userId;

    @Schema(description = "创作者编号名")
    private String userName;

    @Schema(description = "作者头像")
    private String headImgUrl;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "相关标签集合")
    List<TagBaseInfo> tagBaseInfoList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "其他知识记录信息")
    private KnowledgeRecordsInfo knowledgeRecordsInfo;
}
