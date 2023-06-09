package org.spring.cloud.alibaba.learn.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

/**
 * 自定义spring security 配置信息
 *
 * @author xianglujun
 * @date 2023/5/26 15:04
 */
@EnableWebSecurity(debug = true)
@Configuration
@EnableMethodSecurity
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web
                .ignoring() // 配置不需要鉴权的地址
                .antMatchers("/static/**"); // 过滤所有静态地址配置
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(false) // 是否在鉴权成功之后, 将密码清楚
                .inMemoryAuthentication() // 使用内存用户设置
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin") // 设置用户名
                .password("8888") // 设置密码
                .roles("USER", "ADMIN"); // 设置角色列表
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin((form) -> form.loginPage("/login") // 登录的请求地址
                .permitAll(true)
                .passwordParameter("pwd") // 设置密码参数名称
                .usernameParameter("um") // 设置用户名称参数名称
                .defaultSuccessUrl("/index") // 成功之后跳转地址
                .failureUrl("/login?error")); // 开启表单登录;

        // 禁用csrf
        http.csrf().disable();
        // 开启basic功能
        http.httpBasic();

        // 匿名登陆
//        http.anonymous();

        // 配置登出逻辑
        http.logout(logout -> {
            // 配置登出url
            logout.logoutUrl("/user/logout");
//            logout.logoutSuccessUrl("/usr/logout/index").permitAll();
            // 清楚指定cookie
            logout.addLogoutHandler(new CookieClearingLogoutHandler("cookie-name"));
            logout.deleteCookies("cookie-name");
            // clear-site-data配置
            logout.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)));
        });

        // 开始remember-me功能
        http.rememberMe((config) -> {
            config.key("test-remember-me");
        });
        http.authorizeRequests((registry) -> {
            // 允许匿名访问
//            registry.antMatchers("/index").authenticated();
            registry.antMatchers("/usr/logout/index").permitAll();
            registry.antMatchers("/**").authenticated();
        });
    }
}
