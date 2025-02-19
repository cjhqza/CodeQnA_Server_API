package com.cjh.codeqna.model.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 前端评论数据传输对象
 * @Create: 2025-02-17 17:25
 */
@Data
@Schema(description = "评论搜索请求参数")
public class DtCommentDto {
    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "开始时间")
    private String beginCreateTime;

    @Schema(description = "结束时间")
    private String endCreateTime;
}
