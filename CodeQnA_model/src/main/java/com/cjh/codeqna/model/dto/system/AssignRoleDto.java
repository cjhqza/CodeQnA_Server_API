package com.cjh.codeqna.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 人员分配角色数据传输对象
 * @Create: 2024-12-30 21:47
 */
@Data
@Schema(description = "分配角色请求参数")
public class AssignRoleDto {
    @Schema(description = "人员id")
    private Long userId;

    @Schema(description = "角色id的List集合")
    private List<Long> roleIdsList;
}
