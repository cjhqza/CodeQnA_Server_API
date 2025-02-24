package com.cjh.codeqna.common.log.annotation;

import com.cjh.codeqna.common.log.enums.OperatorType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: cjh
 * @Description: 自定义日志注解类
 * @Create: 2025-02-24 15:20
 */
@Target({ElementType.METHOD})   // 在方法上使用
@Retention(RetentionPolicy.RUNTIME) // 生命周期
public @interface Log {
    @Schema(description = "模块名称")
    public String title();
    @Schema(description = "操作人类别")
    public OperatorType operatorType() default OperatorType.MANAGE;
    @Schema(description = "业务类型（0其它 1新增 2修改 3删除）")
    public int businessType();
    @Schema(description = "是否保存请求的参数")
    public boolean isSaveRequestData() default true;
    @Schema(description = "是否保存响应的参数")
    public boolean isSaveResponseData() default true;
}
