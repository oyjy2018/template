package com.ydc.cgj.ydhc.web;

import com.ydc.beans.filter.FilterInterceptConfig;
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

//@Configuration  //标注一个类是配置类，spring boot在扫到这个注解时自动加载这个类相关的功能，比如前面的文章中介绍的配置AOP和拦截器时加在类上的Configuration
//@EnableAutoConfiguration //启用自动配置 该框架就能够进行行为的配置，以引导应用程序的启动与运行, 根据导入的starter-pom 自动加载配置
//@EnableHystrix  //断路器
@EnableFeignClients  //使用feign需要添加此注释
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class
        ,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(value = {"com.ydc.cgj.ydhc.web", "com.ydc.beans.*"},
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {FilterInterceptConfig.class,AppShiroConfig.class})})
public class CgjYdhcWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgjYdhcWebApplication.class, args);
    }
}
