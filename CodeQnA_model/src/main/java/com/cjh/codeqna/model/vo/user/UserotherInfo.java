package com.cjh.codeqna.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户其他相关信息响应数据对象
 * @Create: 2025-04-12 19:27
 */
@Data
@Schema(description = "用户其他相关信息响应数据对象")
public class UserotherInfo {
    @Schema(description = "获赞数")
    private Long appreciateCount;

    @Schema(description = "关注数")
    private Long followCount;

    @Schema(description = "粉丝数")
    private Long fansCount;

    @Schema(description = "是否已关注")
    private Boolean isFollow;
}
