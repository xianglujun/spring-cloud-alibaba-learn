package org.spring.cloud.alibaba.learn.nacos.config.configuration;

import org.spring.cloud.alibaba.learn.nacos.config.properties.ConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xianglujun
 * @date 2023/4/19 15:47
 */
@Configuration
@EnableConfigurationProperties({ConfigProperties.class})
public class ConfigConfiguration {

    //    @Bean
    public ConfigProperties configProperties() {
        return new ConfigProperties();
    }
}
