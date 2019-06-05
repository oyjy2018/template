package com.ydc.service.sys;

import com.ydc.beans.filter.FilterInterceptConfig;
import com.ydc.beans.shiro.app.AppShiroConfig;
import com.ydc.beans.shiro.web.AuthApplicationRunner;
import com.ydc.beans.shiro.web.WebShiroConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class
                        ,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(value = {"com.ydc.dao.*", "com.ydc.service.sys", "com.ydc.beans.*"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {WebShiroConfig.class,AuthApplicationRunner.class,AppShiroConfig.class,FilterInterceptConfig.class}))
public class ServiceSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSysApplication.class, args);
    }

}

