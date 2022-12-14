package com.felix.felixapis.configs;

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
//                .addResourceLocations("file:/app/target/classes/static/");//working for heroku
//        .addResourceLocations("file:\\"+ System.getProperty("user.dir") +"\\src\\main\\resources\\static\\"); //original
                .addResourceLocations("file:/home/ec2-user/static/");
    }
}
