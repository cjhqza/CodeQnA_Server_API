package com.cjh.codeqna.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: cjh
 * @Description: 设置不需要进行登录验证的配置类
 * @Create: 2024-12-26 15:34
 */

@Data
@ConfigurationProperties(prefix = "codeqna.auth")
public class ManagerUserProperties {
    private List<String> noAuthUrls;

}
