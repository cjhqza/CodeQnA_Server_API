package com.cjh.codeqna.report;

import com.cjh.codeqna.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: cjh
 * @Description: 举报服务启动类
 * @Create: 2025-03-28 11:30
 */
@SpringBootApplication
@EnableUserLoginAuthInterceptor
public class ReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }
}
