package com.cjh.codeqna.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端角色数据传输对象
 * @Create: 2024-12-27 16:45
 */
@Data
@Schema(description = "角色搜索请求参数")
public class SysRoleDto {
    @Schema(description = "角色名称")
    private String roleName ;
}
