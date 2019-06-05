package com.ydc.mongodb.cgj.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig{
    @Autowired
    Environment environment;

    @Primary
    @Bean(name="cgjMongoProperties")
   // @ConfigurationProperties(prefix="spring.data.mongodb.cgj")
    public MongoProperties cgjMongoProperties() {
        MongoProperties mongoProperties=new MongoProperties();
        mongoProperties.setUri(environment.getProperty("spring.data.mongodb.cgj.uri"));

        return mongoProperties;
    }

    @Bean
    @Primary
    public MongoDbFactory cgjFactory() throws Exception {
        //如果你的 配置文件中 使用的是 uri 这里就配置 uri 网上很多这里配置错了，导致无法正常运行。
        return new SimpleMongoDbFactory(new MongoClientURI(cgjMongoProperties().getUri()));
    }


    @Primary
    @Bean(name = "cgjMongoTemplate")
    public MongoTemplate cgjMongoTemplate() throws Exception {
        MongoDbFactory mongoDbFactory = cgjFactory();
        return new MongoTemplate(mongoDbFactory);
    }

}
