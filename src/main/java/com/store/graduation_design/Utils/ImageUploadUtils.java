package com.store.graduation_design.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageUploadUtils implements WebMvcConfigurer {
    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //    告知系统static 当成 静态资源访问
//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:"+path);
//    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img/userHead/**").addResourceLocations("file:E:\\WebWorkspace\\spring-test\\src\\main\\resources\\static\\img\\userHead\\");
    }
}