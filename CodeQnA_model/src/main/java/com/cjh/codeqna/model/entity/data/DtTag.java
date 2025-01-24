package com.cjh.codeqna.model.entity.data;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import com.cjh.codeqna.model.entity.system.SysMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 数据标签信息实体类
 * @Create: 2025-01-16 22:23
 */
@Data
@Schema(description = "数据标签信息实体类")
public class DtTag extends BaseEntity {
    @Schema(description = "所属上级标签")
    private Long parentId;
    @Schema(description = "代表图")
    private String img;
    @Schema(description = "标签名称")
    private String tagName;
    @Schema(description = "标签介绍")
    private String tagIntro;
    // 下级标签
    @Schema(description = "子节点")
    private List<DtTag> children;
}
