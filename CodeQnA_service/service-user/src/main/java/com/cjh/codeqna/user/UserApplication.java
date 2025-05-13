package com.cjh.codeqna.user;

import com.cjh.codeqna.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: cjh
 * @Description: 用户服务启动类
 * @Create: 2025-03-09 21:58
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cjh.codeqna"})
@EnableFeignClients(basePackages = {"com.cjh.codeqna.feign"})
@EnableUserLoginAuthInterceptor
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
