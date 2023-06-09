package org.spring.cloud.alibaba.learn.security.authorization.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Oauth2.0 资源服务器应用
 *
 * @author xianglujun
 * @date 2023/6/7 10:44
 */
@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
}
