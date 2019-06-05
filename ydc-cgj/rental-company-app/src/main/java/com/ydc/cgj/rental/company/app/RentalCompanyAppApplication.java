package com.ydc.cgj.rental.company.app;

import com.ydc.beans.shiro.app.AppShiroConfig;
import com.ydc.beans.shiro.web.AuthApplicationRunner;
import com.ydc.beans.shiro.web.WebShiroConfig;
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
@ComponentScan(value = {"com.ydc.cgj.rental.company.app", "com.ydc.beans.*"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {AuthApplicationRunner.class,WebShiroConfig.class})})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        ,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class RentalCompanyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalCompanyAppApplication.class, args);
    }

}

