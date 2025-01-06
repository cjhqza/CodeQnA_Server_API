package com.cjh.codeqna.model.entity.system;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 菜单实体类
 * @Create: 2025-01-06 21:37
 */
@Data
@Schema(description = "菜单实体类")
public class SysMenu extends BaseEntity {
    @Schema(description = "父节点id")
    private Long parentId;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "组件名称")
    private String component;

    @Schema(description = "排序值")
    private Integer sortValue;

    @Schema(description = "状态(0:禁止,1:正常)")
    private Integer status;

    // 下级列表
    @Schema(description = "子节点")
    private List<SysMenu> children;
}
