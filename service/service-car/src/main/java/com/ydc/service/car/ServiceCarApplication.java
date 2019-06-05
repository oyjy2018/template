package com.ydc.service.car;

import com.ydc.beans.commom.EnvironmentFactory;
import com.ydc.beans.commom.WeiXinUtil;
import com.ydc.beans.config.RocketMQConfig;
import com.ydc.beans.config.SystemPropertiesConfig;
import com.ydc.beans.filter.FilterInterceptConfig;
import com.ydc.beans.shiro.app.AppShiroConfig;
import com.ydc.beans.shiro.web.AuthApplicationRunner;
import com.ydc.beans.shiro.web.WebShiroConfig;
import com.ydc.model.mongodb.common.ServerCarLog;
import com.ydc.mongodb.cgj.dao.ServerCarLogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class,
        MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@ComponentScan(value = {"com.ydc.dao.*", "com.ydc.service.car", "com.ydc.beans.*","com.ydc.mongodb.*"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = {WebShiroConfig.class,AuthApplicationRunner.class,AppShiroConfig.class,FilterInterceptConfig.class}))
public class ServiceCarApplication {
    private static Logger logger = LogManager.getLogger(ServiceCarApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(ServiceCarApplication.class, args);
    }

    @Resource
    ServerCarLogDao serverCarLogDao;
    @ResponseBody
    @RequestMapping("/test")
    public String queryUserList() {
        ServerCarLog serverCarLog=new ServerCarLog();
        Long id=System.currentTimeMillis();
        serverCarLog.setId(id);
        serverCarLog.setRequestMapping("中国");
        serverCarLog.setCreateBy("1146441");
        serverCarLog.setCreateTime(new Date());
        serverCarLogDao.save(serverCarLog);

        return serverCarLogDao.findById(id).toString();
    }


}
