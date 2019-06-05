package com.ydc.quartz.task;

import com.ydc.commom.util.DateUtil;
import com.ydc.commom.util.ServiceFactoryUtil;
import com.ydc.quartz.service.SysErrorLogHttpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * http请求错误重试定时任务
 *
 * 每三分钟执行一次
 */
public class AutoReHttpErrorJob implements Job {
    private static final Logger logger = LogManager.getLogger(AutoReHttpErrorJob.class);

    private static SysErrorLogHttpService sysErrorLogHttpService = (SysErrorLogHttpService) ServiceFactoryUtil.getBean("sysErrorLogHttpService");
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("subject: {}, time: {}","-------------------------------http请求错误重试定时任务---------------------------------", DateUtil.formatDateAndTime(new Date()));
        sysErrorLogHttpService.job();
    }
}
