package com.cjh.codeqna.upload;

import com.cjh.codeqna.upload.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author: cjh
 * @Description: 文件上传服务
 * @Create: 2025-04-11 19:09
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {MinioProperties.class})
public class UpLoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpLoadApplication.class, args);
    }
}
