package com.cjh.codeqna.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: cjh
 * @Description: 登录响应结果实体类
 * @Create: 2024-12-25 20:42
 */
@Data
@Schema(description = "登录响应结果实体类")
public class LoginVo {
    @Schema(description = "令牌")
    private String token ;

    @Schema(description = "刷新令牌,可以为空")
    private String refresh_token ;
}
