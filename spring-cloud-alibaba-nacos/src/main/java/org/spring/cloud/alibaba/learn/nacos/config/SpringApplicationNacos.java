package org.spring.cloud.alibaba.learn.nacos.config;

import org.spring.cloud.alibaba.learn.nacos.config.properties.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xianglujun
 * @date 2023/3/28 15:50
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringApplicationNacos {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationNacos.class, args);
        System.out.println(applicationContext);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
//        System.out.println(environment.getProperty("config.name"));
//        System.out.println(environment.getProperty("config.app"));
//        System.out.println(environment.getProperty("config.bootstrap"));

        ConfigProperties configProperties = applicationContext.getBean(ConfigProperties.class);
        System.out.println(configProperties);
        System.out.println(Arrays.toString(applicationContext.getBeanNamesForType(ConfigProperties.class)));

        new ScheduledThreadPoolExecutor(1)
                .scheduleAtFixedRate(() -> {
                    System.out.println(environment.getProperty("config.test"));
                    System.out.println(configProperties);
                }, 0, 5, TimeUnit.SECONDS);
    }
}
