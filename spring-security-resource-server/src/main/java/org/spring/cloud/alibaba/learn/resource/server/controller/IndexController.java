package org.spring.cloud.alibaba.learn.resource.server.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianglujun
 * @date 2023/6/9 15:46
 */
@RestController
public class IndexController {
    @GetMapping("/findAll")
    @PreAuthorize("hasRole('USER')")
    public String findAll() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "查询所有权限信息..";
    }
}
