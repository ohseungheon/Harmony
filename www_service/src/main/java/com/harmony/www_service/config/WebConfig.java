package com.harmony.www_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.ingredients-dir}")
    private String ingredientsDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**") // 메뉴 주소
                .addResourceLocations("file:" + uploadDir + "/");

        registry.addResourceHandler("/img/ingredient/**") // 코드상에서 이미지파일을 불러오는 가상공간
                .addResourceLocations("file:" + ingredientsDir + "/"); // 실제로 파일이 저장되는 공간

    }

}
