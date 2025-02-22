package com.cjh.codeqna.model.vo.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @Author: cjh
 * @Description: 数据知识响应结果实体类
 * @Create: 2025-01-26 0:03
 */
@Data
public class DtKnowledgeVo {
    @Schema(description = "唯一标识")
    private Long id;
    @Schema(description = "知识类型(0：问答贴 1：文章)")
    private Integer type;
    @Schema(description = "创作者编号名")
    private String userName;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "创作内容")
    private String content;
    @Schema(description = "审批状态（-1：审批不通过【默认不通过，此时认作为草稿】； 0：正在审批【不认作草稿】； 1：审批通过【正式上架】）")
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "相关标签集合")
    private Set<String> tagNames;
}
