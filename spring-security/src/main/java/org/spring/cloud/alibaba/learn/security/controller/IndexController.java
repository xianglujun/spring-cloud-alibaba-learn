package org.spring.cloud.alibaba.learn.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianglujun
 * @date 2023/5/26 10:27
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
