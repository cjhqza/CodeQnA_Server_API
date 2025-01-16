package com.cjh.codeqna.model.entity.data;

import com.cjh.codeqna.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 数据用户信息实体类
 * @Create: 2025-01-15 15:27
 */
@Data
@Schema(description = "数据用户信息实体类")
public class DtUser extends BaseEntity {

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private Integer sex;

    @Schema(description = "头像")
    private String headImgUrl;

    @Schema(description = "背景图")
    private String bgImgUrl;

    @Schema(description = "个人介绍")
    private String selfIntro;

    @Schema(description = "个性签名")
    private String signture;

    @Schema(description = "状态（1：正常 0：停用）")
    private Integer status;
}
