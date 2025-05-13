package com.cjh.codeqna.common.anno;

import com.cjh.codeqna.common.config.UserWebMvcConfiguration;
import com.cjh.codeqna.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: cjh
 * @Description: 用户登录拦截器注解
 * @Create: 2025-03-04 16:02
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = { UserLoginAuthInterceptor.class , UserWebMvcConfiguration.class})
public @interface EnableUserLoginAuthInterceptor {
}
