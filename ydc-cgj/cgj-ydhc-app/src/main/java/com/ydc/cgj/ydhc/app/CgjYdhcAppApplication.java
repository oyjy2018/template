package com.ydc.cgj.ydhc.app;

import com.ydc.beans.filter.FilterInterceptConfig;
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

//@EnableHystrix  //断路器
@EnableFeignClients  //使用feign需要添加此注释
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        ,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(value = {"com.ydc.cgj.ydhc.app", "com.ydc.beans.*"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {AuthApplicationRunner.class,WebShiroConfig.class,FilterInterceptConfig.class})})
public class CgjYdhcAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CgjYdhcAppApplication.class, args);
    }

}
