package org.spring.cloud.alibaba.learn.nacos.config.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * @author xianglujun
 * @date 2023/4/27 15:15
 */
@Getter
@Service
@RefreshScope
public class NacosValueService {

    @Value("${config.age}")
    private Integer age;
    private volatile boolean isStop = false;

    @PreDestroy
    public void destroy() {
        System.out.println("执行destroy方法");
    }
}
