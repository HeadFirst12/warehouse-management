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
        registry.addViewController("/set/info").setViewName("views/set/info");

        //用户密码修改
        registry.addViewController("/set/password").setViewName("views/set/password");

        //用户登录页面
        registry.addViewController("/user/login").setViewName("views/user/login");

        //消息中心
        registry.addViewController("/app/message/index").setViewName("views/app/message/index");

        //用户管理
        registry.addViewController("/user/administrators/list").setViewName("views/user/administrators/list");

        //角色权限管理
        registry.addViewController("/user/administrators/role").setViewName("views/user/administrators/role");

        //添加和编辑用户
        registry.addViewController("/user/administrators/adminform").setViewName("views/user/administrators/adminform");

        //出库列表
        registry.addViewController("/app/content/outlist").setViewName("views/app/content/outlist");

        //入库列表
        registry.addViewController("/app/content/entrylist").setViewName("views/app/content/entrylist");

        //添加出库日志编辑页面
        registry.addViewController("/app/workorder/outlistform").setViewName("views/app/workorder/outlistform");

        //添加入库日志编辑页面
        registry.addViewController("/app/workorder/entrylistform").setViewName("views/app/workorder/entrylistform");

        //库存管理
        registry.addViewController("/app/content/goodslist").setViewName("views/app/content/goodslist");

        //库存日志列表
        registry.addViewController("/app/content/loglist").setViewName("views/app/content/loglist");

        //添加库存页面
        registry.addViewController("/app/workorder/addgoodsform").setViewName("views/app/workorder/addgoodsform");


    }

    //配置静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
