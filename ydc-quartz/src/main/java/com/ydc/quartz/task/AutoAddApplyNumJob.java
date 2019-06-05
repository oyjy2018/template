package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.AutoAddApplyNumService;
import com.ydc.quartz.service.AutoCloseOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Title 自动增加申请人数
 * @date 2019/1/24
 */
public class AutoAddApplyNumJob implements Job {
    private static final Logger logger = LogManager.getLogger(AutoAddApplyNumJob.class);
    private static AutoAddApplyNumService autoAddApplyNumService = (AutoAddApplyNumService) ServiceFactoryUtil.getBean("autoAddApplyNumService");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-自动增加申请人数-----------------");
        try {
            autoAddApplyNumService.addApplyNum();
        } catch (Exception e) {
            logger.error(e);
        }
        logger.info("------------------任务结束执行-自动增加申请人数-----------------");
    }
}
