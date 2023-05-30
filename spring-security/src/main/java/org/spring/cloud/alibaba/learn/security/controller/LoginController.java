package org.spring.cloud.alibaba.learn.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xianglujun
 * @date 2023/5/26 16:07
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String processing() {
        return "processing";
    }
}
