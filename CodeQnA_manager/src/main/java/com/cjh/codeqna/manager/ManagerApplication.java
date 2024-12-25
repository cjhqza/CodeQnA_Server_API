package com.cjh.codeqna.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: cjh
 * @Description: CodeQnA_manager模块的启动类
 * @Create: 2024-12-25 17:18
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cjh.codeqna"})  // 由于SpringBoot默认扫描当前包，所以通过设置扫描包起点，能够扫描到Knife4j类所在的包
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
