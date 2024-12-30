package com.cjh.codeqna.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端人员数据传输对象
 * @Create: 2024-12-29 14:50
 */
@Data
@Schema(description = "人员搜索请求参数")
public class SysUserDto {
    @Schema(description = "人员名称")
    private String userName;

    @Schema(description = "开始时间")
    private String beginCreateTime;

    @Schema(description = "结束时间")
    private String endCreateTime;
}
