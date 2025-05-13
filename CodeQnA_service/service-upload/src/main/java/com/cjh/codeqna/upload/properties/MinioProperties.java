package com.cjh.codeqna.upload.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: cjh
 * @Description: 设置Minio配置类
 * @Create: 2024-12-29 21:44
 */
@Data
@ConfigurationProperties(prefix = "codeqna.minio")
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;
}
