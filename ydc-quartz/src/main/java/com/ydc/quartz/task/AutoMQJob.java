package com.ydc.quartz.task;

import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.SysErrorLogRocketmqService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * MQ失败消息处理
 * 自动处理MQ失败消息提供-每隔1分钟轮询
 * @author
 * @create 2018-11-13 11:21
 **/
public class AutoMQJob implements Job {

    private static final Logger logger = LogManager.getLogger(AutoMQJob.class);

    private static SysErrorLogRocketmqService sysErrorLogRocketmqService = (SysErrorLogRocketmqService) ServiceFactoryUtil.getBean("sysErrorLogRocketmqService");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("subject:{}","-------------------------------开始MQ失败消息处理---------------------------------");
        try{
            sysErrorLogRocketmqService.job();
        }catch (Exception e){
            logger.error("subject:{},e:{}","MQ失败消息处理异常",e);
        }
        logger.info("subject:{}","-------------------------------结束MQ失败消息处理---------------------------------");
    }
}
