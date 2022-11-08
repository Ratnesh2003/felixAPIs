package com.felix.felixapis.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/stream-movie/**")
                .addResourceLocations("file:/app/target/classes/static/");
//        .addResourceLocations("file:\\"+ System.getProperty("user.dir") +"\\target\\classes\\resources\\static\\");
//        .addResourceLocations("file:\\"+ System.getProperty("user.dir") +"\\src\\main\\resources\\static\\"); original
//        .addResourceLocations("file:/"+ System.getProperty("user.dir") +"/target/classes/static/"); for linux
    }
}
