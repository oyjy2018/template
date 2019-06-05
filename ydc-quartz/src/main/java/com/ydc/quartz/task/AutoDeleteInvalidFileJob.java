package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.DeleteInvalidFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 删除无效文件-每天凌晨1点执行一遍
 *
 * @author
 * @create 2018-12-24 10:57
 **/
public class AutoDeleteInvalidFileJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoNotarizeReceivingJob.class);

    private static DeleteInvalidFileService deleteInvalidFileService = (DeleteInvalidFileService) ServiceFactoryUtil.getBean("deleteInvalidFileService");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("------------------任务开始执行-删除无效文件-每天凌晨1点执行一遍-----------------");
        try {
            deleteInvalidFileService.deleteInvalidFile();
        } catch (Exception e) {
            logger.error("删除无效文件异常",e);
        }
        logger.info("------------------任务结束执行-删除无效文件-每天凌晨1点执行一遍-----------------");
    }
}
