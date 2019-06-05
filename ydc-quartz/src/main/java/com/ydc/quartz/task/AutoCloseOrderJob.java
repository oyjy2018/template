package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.AutoCloseOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自动关闭30分钟内未支付的订单(每隔1分钟执行)
 * @author gongjin
 * @create 2018-09-18 9:47
 **/
public class AutoCloseOrderJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);

    private static AutoCloseOrderService autoCloseOrderService = (AutoCloseOrderService) ServiceFactoryUtil.getBean("autoCloseOrderService");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-自动关闭未支付的订单-----------------");
        try {
            autoCloseOrderService.job();
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info("------------------任务结束执行-自动关闭内未支付的订单-----------------");
    }
}
