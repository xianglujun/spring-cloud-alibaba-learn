package org.spring.cloud.alibaba.learn.security.authorization.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * web安全访问配置
 *
 * @author xianglujun
 * @date 2023/6/7 17:25
 */
@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨域保护
        http.csrf().disable();
        http.formLogin().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/login", "/logout").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin") // 设置用户名
                .password("{noop}8888") // 设置密码
                .roles("USER", "ADMIN"); // 设置角色列表
    }


}
