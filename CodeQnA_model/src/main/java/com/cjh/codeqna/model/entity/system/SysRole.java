package com.cjh.codeqna.model.entity.system;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 角色实体类
 * @Create: 2024-12-27 16:43
 */
@Data
@Schema(description = "角色实体类")
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色编码")
    private String roleCode;

    @Schema(description = "描述")
    private String description;
}
