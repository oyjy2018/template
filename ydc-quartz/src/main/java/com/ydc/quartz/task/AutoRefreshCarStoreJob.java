package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.RefreshCarStoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自动获取门店
 *
 * @author
 * @create 2019-01-21 11:27
 **/
public class AutoRefreshCarStoreJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoRefreshCarStoreJob.class);

    private static RefreshCarStoreService refreshCarStoreService = (RefreshCarStoreService) ServiceFactoryUtil.getBean("refreshCarStoreService");


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-自动获取门店-----------------");
        try{
            refreshCarStoreService.job();
        }catch (Exception e){
            logger.error("subject:{},e:{}","自动获取门店异常",e);
        }
        logger.info("------------------任务结束执行-自动获取门店-----------------");
    }
}
