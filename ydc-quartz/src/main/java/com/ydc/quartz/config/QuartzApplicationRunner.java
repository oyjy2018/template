package com.ydc.quartz.config;

import com.ydc.quartz.service.init.InitCache;
import com.ydc.quartz.service.init.InitRSAKey;
import com.ydc.quartz.service.init.InitUserAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化定时任务
 * @author gongjin
 * @create 2018-09-10 13:25
 **/
@Component
@Order(10)
public class QuartzApplicationRunner implements ApplicationRunner {

    private static final Logger logger = LogManager.getLogger(QuartzApplicationRunner.class);

    @Autowired
    InitCache initCache;
  /*  @Autowired
    InitRSAKey initRSAKey;*/
    @Autowired
    InitUserAuth initUserAuth;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("---------------------开始初始化任务--------------------");
        initCache.initRun();
      //  initRSAKey.init();
        initUserAuth.initRun();
        logger.info("---------------------结束初始化任务--------------------");
    }
}
