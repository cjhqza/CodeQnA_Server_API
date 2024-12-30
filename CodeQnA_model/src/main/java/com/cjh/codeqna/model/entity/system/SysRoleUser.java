package com.cjh.codeqna.model.entity.system;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 人员分配角色实体类
 * @Create: 2024-12-30 21:50
 */
@Data
@Schema(description = "人员分配角色实体类")
public class SysRoleUser extends BaseEntity {
    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "人员id")
    private Long userId;
}
