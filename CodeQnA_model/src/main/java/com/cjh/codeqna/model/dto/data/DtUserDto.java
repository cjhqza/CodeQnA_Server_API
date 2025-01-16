package com.cjh.codeqna.model.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端用户数据传输对象
 * @Create: 2025-01-15 15:36
 */
@Data
@Schema(description = "用户搜索请求参数")
public class DtUserDto {
    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "开始时间")
    private String beginCreateTime;

    @Schema(description = "结束时间")
    private String endCreateTime;
}
