package org.spring.cloud.alibaba.learn.sentinel.controller;

import lombok.RequiredArgsConstructor;
import org.spring.cloud.alibaba.learn.sentinel.service.SayHelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianglujun
 * @date 2023/5/9 10:09
 */
@RestController
@RequiredArgsConstructor
public class HelloController {

    private final SayHelloService sayHelloService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return sayHelloService.sayHello(name);
    }

}
