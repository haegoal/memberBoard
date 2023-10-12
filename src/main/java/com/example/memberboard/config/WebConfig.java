package com.example.memberboard.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // 스프링할 때 xml에 upload file 셋팅한것처럼 해줘야해서 클래스로 만들어줌
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터로 등록할 클래스
                .order(1) // 해당 인터셉터의 우선순위
                .addPathPatterns("/member/admin", "/board") // 인터셉터로 체크할 주소(모든주소)
                .excludePathPatterns("/", "/member/save", "/member/login",
                        "/js/**", "/css/**", "/member/image/**", "/image/**",
                        "/*.ico", "/favicon/**"); // 인터셉터 검증을 하지 않을 주소
    }


    private String resourcePath = "/upload/**"; // html 에서 접근할 경로
    private String savePath = "file:///C:/springboot_img/"; // 실제 파일이 저장된 경로
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}