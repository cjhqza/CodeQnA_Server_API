package com.cjh.codeqna.manager.config;

import com.cjh.codeqna.manager.typehandler.TagTypeHandler;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cjh
 * @Description: 创建MyBatis配置类
 * @Create: 2025-02-16 15:29
 */

@Configuration
public class MyBatisConfiguration {
    @Bean
    public ConfigurationCustomizer myBatisConfigurationCustomizer() {
        return configuration -> {
            configuration.getTypeHandlerRegistry().register(TagTypeHandler.class);
        };
    }
}
