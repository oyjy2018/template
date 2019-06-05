package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.AutoNotarizeReceivingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 确认收货（X天后，系统自动确认收货）
 *
 * @author gongjin
 * @create 2018-09-10 13:13
 **/
public class AutoNotarizeReceivingJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);

    private static AutoNotarizeReceivingService autoNotarizeReceivingService = (AutoNotarizeReceivingService) ServiceFactoryUtil.getBean("autoNotarizeReceivingService");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行确认收货（X天后，系统自动确认收货）-----------------");
        autoNotarizeReceivingService.job();
        logger.info("------------------任务结束执行确认收货（X天后，系统自动确认收货）-----------------");
    }
}
