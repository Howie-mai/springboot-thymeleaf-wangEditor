package com.zhku.mh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
public class WangeditorDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WangeditorDemoApplication.class, args);
    }

    @Bean
    CommonsMultipartResolver commonsMultipartResolver (){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(52428800);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
