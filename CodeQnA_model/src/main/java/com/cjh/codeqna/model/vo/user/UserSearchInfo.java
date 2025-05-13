package com.cjh.codeqna.model.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 用户搜索响应数据对象
 * @Create: 2025-04-10 21:09
 */
@Data
@Schema(description = "用户搜索信息数据对象")
public class UserSearchInfo {
    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "头像")
    private String headImgUrl;

    @Schema(description = "背景图")
    private String bgImgUrl;

    @Schema(description = "自我介绍")
    private String selfIntro;

    @Schema(description = "个性签名")
    private String signture;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private String createTime;
}
