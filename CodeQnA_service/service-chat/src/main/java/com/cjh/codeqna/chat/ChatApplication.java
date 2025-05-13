package com.cjh.codeqna.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: cjh
 * @Description: 聊天服务启动类
 * @Create: 2025-03-28 11:32
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cjh.codeqna.feign"})
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
