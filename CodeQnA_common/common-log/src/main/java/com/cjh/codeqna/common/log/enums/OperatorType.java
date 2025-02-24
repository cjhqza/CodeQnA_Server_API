package com.cjh.codeqna.common.log.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author: cjh
 * @Description: 操作人类型枚举类
 * @Create: 2025-02-24 15:24
 */
public enum OperatorType {
    @Schema(description = "其他")
    OTHER,
    @Schema(description = "后台用户")
    MANAGE,
    @Schema(description = "手机端用户")
    MOBILE
}
