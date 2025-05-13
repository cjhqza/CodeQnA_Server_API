package com.cjh.codeqna.comment;

import com.cjh.codeqna.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: cjh
 * @Description: 答复评论启动类
 * @Create: 2025-03-28 11:26
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.cjh.codeqna.feign"})
@EnableUserLoginAuthInterceptor
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }
}
