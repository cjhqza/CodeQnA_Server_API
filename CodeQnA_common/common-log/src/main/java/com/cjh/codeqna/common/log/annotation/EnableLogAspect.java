package com.cjh.codeqna.common.log.annotation;

import com.cjh.codeqna.common.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: cjh
 * @Description: 日志切面启动类
 * @Create: 2025-02-24 15:31
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = LogAspect.class)    // 通过Import注解导入日志切面类到Spring容器中
public @interface EnableLogAspect {
}
