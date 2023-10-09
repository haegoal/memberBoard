package com.example.memberboard.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 스프링할 때 xml에 upload file 셋팅한것처럼 해줘야해서 클래스로 만들어줌
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // html 에서 접근할 경로
    private String savePath = "file:///C:/springboot_img/"; // 실제 파일이 저장된 경로
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}