package com.ydc.quartz.service.init;

import com.ydc.dao.cgj.common.TaskDetailsDao;
import com.ydc.model.cgj.TaskDetails;
import com.ydc.quartz.config.QuartzScheduler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化定时任务
 *
 * @author gongjin
 * @create 2018-09-10 16:35
 **/
@Component
public class InitScheduler {

    private static final Logger logger = LogManager.getLogger(InitScheduler.class);

    @Autowired
    TaskDetailsDao taskDetailsDao;
    @Autowired
    Scheduler scheduler;

    public void initQuartz(){
        List<TaskDetails> list = taskDetailsDao.getValidTaskDetail();
        logger.info("subject:初始化定时器,size:"+(list == null ? 0 : list.size()));
        if(list == null || list.isEmpty())return;
        String taskId = null;
        String taskDetail = null;
        Class objAddress =  null;
        String taskCron = null;
        for (TaskDetails taskDetails : list) {
            taskId = taskDetails.getTaskId();
            taskDetail = taskDetails.getTaskDetail();
            try{
                objAddress = Class.forName(taskDetails.getObjAddress());
            }catch (Exception e){
                logger.error("异常"+taskDetails.getObjAddress(),e);
                continue;
            }
            taskCron = taskDetails.getTaskCron();
            logger.info("subject:初始化定时器,taskId:"+taskId+",taskDetail:"+taskDetail+",objAddress："+objAddress+",taskCron:"+taskCron);
            if(!QuartzScheduler.checkTaskJob(scheduler,taskId)){
                QuartzScheduler.addJob(scheduler,taskId,objAddress,taskCron);
            }
        }
    }
}
