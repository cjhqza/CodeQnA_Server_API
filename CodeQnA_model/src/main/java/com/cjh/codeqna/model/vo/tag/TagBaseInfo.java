package com.cjh.codeqna.model.vo.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 标签基本信息数据
 * @Create: 2025-04-07 16:00
 */

@Data
@Schema(description = "标签基本信息数据")
public class TagBaseInfo {
    @Schema(description = "唯一标识")
    private Long id;

    @Schema(description = "标签名")
    private String tagName;
}
