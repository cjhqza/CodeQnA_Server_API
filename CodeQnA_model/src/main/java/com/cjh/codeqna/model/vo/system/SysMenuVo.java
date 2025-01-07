package com.cjh.codeqna.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 系统菜单响应结果实体类
 * @Create: 2025-01-07 22:11
 */
@Data
@Schema(description = "系统菜单响应结果实体类")
public class SysMenuVo {
    @Schema(description = "系统菜单标题")
    private String title;

    @Schema(description = "系统菜单名称")
    private String name;

    @Schema(description = "系统菜单子菜单列表")
    private List<SysMenuVo> children;
}
