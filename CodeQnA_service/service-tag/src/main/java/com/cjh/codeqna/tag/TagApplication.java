package com.cjh.codeqna.tag;

import com.cjh.codeqna.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: cjh
 * @Description: 标签服务启动类
 * @Create: 2025-03-28 10:32
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cjh.codeqna"})
@EnableFeignClients(basePackages = {"com.cjh.codeqna.feign"})
@EnableUserLoginAuthInterceptor
public class TagApplication {
    public static void main(String[] args) {
        SpringApplication.run(TagApplication.class, args);
    }
}
