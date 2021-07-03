package com.hy.warehousemanagement.config;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author hy
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String SUPER = "super";
    private final static String ADMIN = "admin";
    private final static String NORMAL = "normal";

    @Resource
    PersonnelManagementMapper personnelManagementMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //授权 登录页所有人可以访问 功能页需要权限才能访问
        http.authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/**").hasAnyAuthority(ADMIN)
                .antMatchers("/user/**").hasAnyAuthority(SUPER)
                .antMatchers("/").hasAnyAuthority(NORMAL)
                .antMatchers("/set/**").hasAnyAuthority(NORMAL)
                .antMatchers("/home/**").hasAnyAuthority(NORMAL)
                .antMatchers("/app/**").hasAnyAuthority(NORMAL)
                .anyRequest().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

        //没有权限默认回到登录页面
        http.formLogin()
                .loginPage("/user/login")
                .usernameParameter("userName")
                .passwordParameter("userPassword")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/");

        http.logout();

        http.rememberMe()
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(60 * 60 * 24 * 3);
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Bean
    public PasswordEncoder getPwd() {
        return new BCryptPasswordEncoder();
    }
}
