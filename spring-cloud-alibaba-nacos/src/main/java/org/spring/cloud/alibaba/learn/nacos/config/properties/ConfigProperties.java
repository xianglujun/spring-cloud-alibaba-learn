package org.spring.cloud.alibaba.learn.nacos.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xianglujun
 * @date 2023/4/19 15:21
 */
@Data
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {

    private String name;
    private Integer age;
    private String className;
}
