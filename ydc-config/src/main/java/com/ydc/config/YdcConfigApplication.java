package com.ydc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class YdcConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(YdcConfigApplication.class, args);
    }
}
