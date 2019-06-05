package com.ydc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class YdcEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YdcEurekaApplication.class, args);
    }
}
