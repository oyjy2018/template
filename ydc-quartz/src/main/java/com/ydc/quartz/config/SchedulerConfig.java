package com.ydc.quartz.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author gongjin
 * @create 2018-09-10 15:13
 **/
@Configuration
public class SchedulerConfig {


    /**
     * 初始注入scheduler
     * @return
     * @throws SchedulerException
     */
    @Bean(name = "scheduler")
    @Lazy
    public Scheduler scheduler() throws SchedulerException{
        SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();
        return schedulerFactoryBean.getScheduler();
    }
}
