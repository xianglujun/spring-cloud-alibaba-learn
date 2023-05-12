package org.spring.cloud.alibaba.learn.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author xianglujun
 * @date 2023/5/9 10:10
 */
@Service
public class SayHelloService {

    @SentinelResource(value = "sayHello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
