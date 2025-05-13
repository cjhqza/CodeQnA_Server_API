package com.cjh.codeqna.knowledge;

import com.cjh.codeqna.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: cjh
 * @Description: 知识服务启动类
 * @Create: 2025-03-28 10:32
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cjh.codeqna.feign"})
@EnableUserLoginAuthInterceptor
public class KnowledgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(KnowledgeApplication.class, args);
    }
}
