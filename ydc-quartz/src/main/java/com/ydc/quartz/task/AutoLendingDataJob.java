package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.LendingDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自动查询放款客户-每隔30分钟轮询
 * @author
 * @create 2018-10-26 15:44
 **/
public class AutoLendingDataJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);

    private static LendingDataService lendingDataService = (LendingDataService) ServiceFactoryUtil.getBean("lendingDataService");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-自动查询放款客户-每隔30分钟轮询-----------------");
        try {
            lendingDataService.lendingData();
        } catch (Exception e) {
            logger.error("自动查询放款客户-每隔30分钟轮询异常",e);
        }
        logger.info("------------------任务结束执行-自动查询放款客户-每隔30分钟轮询-----------------");
    }
}
