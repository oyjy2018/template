package com.ydc.cgj.rental.web;


import com.ydc.beans.shiro.app.AppShiroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@EnableFeignClients
@EnableEurekaClient
@ComponentScan(value = {"com.ydc.cgj.rental.web", "com.ydc.beans.*"},
        excludeFilters = {@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,value = {AppShiroConfig.class})})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        ,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class CgjRentalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CgjRentalWebApplication.class, args);
    }
}
