package com.ydc.quartz;

import com.ydc.beans.filter.FilterInterceptConfig;
import com.ydc.beans.shiro.app.AppShiroConfig;
import com.ydc.beans.shiro.web.AuthApplicationRunner;
import com.ydc.beans.shiro.web.WebShiroConfig;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class})
@EnableScheduling
@EnableEurekaClient
@ComponentScan(value = {"com.ydc.dao","com.ydc.quartz","com.ydc.beans.*","com.ydc.commom.util"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {WebShiroConfig.class,AuthApplicationRunner.class,AppShiroConfig.class,FilterInterceptConfig.class}))
public class QuartzApplication {

    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(QuartzApplication.class, args);
    }
}
