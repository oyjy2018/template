package com.ydc.beans.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 上传文件配置
 *
 * @author gongjin
 * @create 2018-09-19 14:46
 **/
@Configuration
public class FileUploadConfig {


    /**
     * 文件上传配置
     * Spring Boot 默认最大request size 为10MB(1048576 bytes)。
     * 需要设置以下两个参数
     * multipart.maxFileSize
     * multipart.maxRequestSize
     * <p>
     * Spring Boot 1.3.x或者之前
     * <p>
     * multipart.maxFileSize=100Mb
     * multipart.maxRequestSize=1000Mb
     * Spring Boot 1.4.x或者之后
     * <p>
     * spring.http.multipart.maxFileSize=100Mb
     * spring.http.multipart.maxRequestSize=1000Mb
     *
     * @return MultipartConfigElement
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize("100Mb");
        // 设置总上传数据总大小
        factory.setMaxRequestSize("1000Mb");
        return factory.createMultipartConfig();
    }
}
