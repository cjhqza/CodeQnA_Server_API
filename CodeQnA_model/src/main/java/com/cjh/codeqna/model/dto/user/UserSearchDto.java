package com.cjh.codeqna.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户搜索传输数据对象
 * @Create: 2025-04-10 22:13
 */
@Data
@Schema(description = "用户搜索传输数据对象")
public class UserSearchDto {
    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "每页条数")
    private Integer pageSize;
}
