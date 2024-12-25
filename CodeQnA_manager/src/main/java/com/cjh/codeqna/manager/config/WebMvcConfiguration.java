package com.cjh.codeqna.manager.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: cjh
 * @Description: 配置类实现跨域
 * @Create: 2024-12-25 22:17
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    // 跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 添加路径规则
                .allowCredentials(true)         // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")     // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
