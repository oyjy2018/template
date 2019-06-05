package com.ydc.cgj.app;


import com.ydc.beans.shiro.web.AuthApplicationRunner;
import com.ydc.beans.shiro.web.WebShiroConfig;
import com.ydc.model.mongodb.common.ServerCarLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
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
@ComponentScan(value = {"com.ydc.cgj.app", "com.ydc.beans.*","com.ydc.mongodb.*"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {AuthApplicationRunner.class,WebShiroConfig.class})})
public class CgjAppApplication {


    static Logger mongoLog=LogManager.getLogger("mongoAppender");
    public static void main(String[] args) {
        SpringApplication.run(CgjAppApplication.class, args);

        ServerCarLog serverCarLog=new ServerCarLog();
        serverCarLog.setRequestMapping("5464644");
        mongoLog.info(serverCarLog);
        mongoLog.info("346444464");
    }

}
