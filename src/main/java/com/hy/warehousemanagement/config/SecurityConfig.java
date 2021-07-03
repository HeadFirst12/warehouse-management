package com.hy.warehousemanagement.config;

import com.hy.warehousemanagement.mapper.PersonnelManagementMapper;
import com.hy.warehousemanagement.pojo.PersonnelManagement;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hy
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    PersonnelManagementMapper personnelManagementMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //授权 登录页所有人可以访问 功能页需要权限才能访问
        http.authorizeRequests()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/").hasRole("USER")
                .antMatchers("/home/console").hasRole("USER")
                .antMatchers("/set/**").hasRole("USER")
                .antMatchers("/app/**").hasRole("USER")
                .antMatchers("/user/administrators/**").hasRole("USER")
                .antMatchers("/warehouse/**").hasRole("USER")
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<PersonnelManagement> personnelManagements = personnelManagementMapper.queryPersonnelManagementList();
        for (PersonnelManagement personnelManagement : personnelManagements) {
            Integer permissionLevel = personnelManagement.getPermissionLevel();
            if (permissionLevel == 1 || permissionLevel == 2 || permissionLevel == 3) {
                String userName = personnelManagement.getUserName();
                String userPassword = personnelManagement.getUserPassword();
                auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                        .withUser(userName).password(new BCryptPasswordEncoder().encode(userPassword)).roles("USER");
            }
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
