package com.hy.warehousemanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拓展springmvc
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //首页路径
        registry.addViewController("/").setViewName("views/index");

        //控制台
        registry.addViewController("/home/console").setViewName("views/home/console");

        //用户基本资料修改
        registry.addViewController("/set/user/info").setViewName("views/set/user/info");

        //用户密码修改
        registry.addViewController("/set/user/password").setViewName("views/set/user/password");

        //退出登录
        registry.addViewController("/user/login").setViewName("views/user/login");

        //消息中心
        registry.addViewController("/app/message/index").setViewName("views/app/message/index");

        //用户管理
        registry.addViewController("/user/administrators/list").setViewName("views/user/administrators/list");

        //角色权限管理
        registry.addViewController("/user/administrators/role").setViewName("views/user/administrators/role");

        //添加用户
        registry.addViewController("/user/administrators/adminform").setViewName("views/user/administrators/adminform");



    }

    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
