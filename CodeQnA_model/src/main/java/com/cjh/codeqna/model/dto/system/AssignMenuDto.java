package com.cjh.codeqna.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: cjh
 * @Description: 角色分配菜单数据传输对象
 * @Create: 2025-01-07 14:39
 */
@Data
@Schema(description = "分配菜单请求参数")
public class AssignMenuDto {
    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "选中的菜单id的集合")
    private List<Map<String , Number>> menuIdList;
}
