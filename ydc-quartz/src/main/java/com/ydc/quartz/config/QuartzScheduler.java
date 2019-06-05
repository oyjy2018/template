package com.ydc.quartz.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务调度处理
 * @author gongjin
 * @create 2018-09-10 13:21
 **/
@Configuration
public class QuartzScheduler {

    private static final Logger logger = LogManager.getLogger(QuartzScheduler.class);

    public static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";


    /**
     * 检查是否存在相同定时器任务
     * @param sched
     * @param jobName
     * @param time
     * @return
     */
    public static Map<String, Object> checkTaskJobTime(Scheduler sched, String jobName, String time){
        Map<String, Object> map = new HashMap<>();
        try {
            TriggerKey triggerKey = new TriggerKey(jobName, JOB_GROUP_NAME);
            CronTrigger cronTrigger = (CronTrigger) sched.getTrigger(triggerKey);
            if(cronTrigger != null){
                //校验调度任务时间是否相同，如果相同，这不给添加
                String oldTime = cronTrigger.getCronExpression();
                if(oldTime.equalsIgnoreCase(time)){
                    map.put("code", 0);
                    map.put("msg", "调度任务已存在");
                }
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}","检查是否存在相同定时器任务异常",e);
        }
        return map;
    }

    /**
     * 检查定时器任务是否存在
     * @param:
     * @return:boolean
     */
    public static boolean checkTaskJob(Scheduler sched,String jobName){
        try {
            TriggerKey triggerKey = new TriggerKey(jobName, JOB_GROUP_NAME);
            CronTrigger cronTrigger = (CronTrigger) sched.getTrigger(triggerKey);
            if(cronTrigger != null){
                return true;
            }
        } catch (Exception e) {
            logger.error("subject:{},e:{}","检查定时任务是否存在异常",e);
        }
        return false;
    }

    /**
     * @Description: 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @author:gongjin
     * @param: sched 调度器
     * @param: jobName 任务名
     * @param: cls 任务
     * @param: time 时间设置，参考quartz说明文档
     * @return:void
     */
    public static void addJob(Scheduler sched,String jobName, @SuppressWarnings("rawtypes") Class cls, String time){
        try {
            JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, JOB_GROUP_NAME)
                    .withSchedule(cronScheduleBuilder).build();
            sched.scheduleJob(jobDetail, cronTrigger);
            //启动
            if(!sched.isShutdown()){
                sched.start();
            }
        } catch (Exception e) {
            logger.error("启动定时器异常",e);
        }
    }

    /**
     * 暂停某个任务
     * @param name
     * @param name
     * @throws SchedulerException
     */
    public static void pauseJob(Scheduler sched,String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, JOB_GROUP_NAME);
        JobDetail jobDetail = sched.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        sched.pauseJob(jobKey);
    }

    /**
     * 暂停所有任务
     * @throws SchedulerException
     */
    public static void pauseAllJob(Scheduler sched) throws SchedulerException {
        sched.pauseAll();
    }

    /**
     * 删除某个任务
     * @param name
     * @throws SchedulerException
     */
    public static void deleteJob(Scheduler sched,String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, JOB_GROUP_NAME);
        JobDetail jobDetail = sched.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        sched.deleteJob(jobKey);
    }

    /**
     * 恢复所有任务
     *
     * @throws SchedulerException
     */
    public static void resumeAllJob(Scheduler sched) throws SchedulerException {
        sched.resumeAll();
    }

    /**
     * 恢复某个任务
     * @param name
     * @throws SchedulerException
     */
    public static void resumeJob(Scheduler sched,String name) throws SchedulerException {
        JobKey jobKey = new JobKey(name, JOB_GROUP_NAME);
        JobDetail jobDetail = sched.getJobDetail(jobKey);
        if (jobDetail == null)
            return;
        sched.resumeJob(jobKey);
    }

    /**
     * 获取Job信息
     *
     * @param name
     * @return
     * @throws SchedulerException
     */
    public static String getJobInfo(Scheduler sched,String name) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(name, JOB_GROUP_NAME);
        CronTrigger cronTrigger = (CronTrigger) sched.getTrigger(triggerKey);
        return String.format("time:%s,state:%s", cronTrigger.getCronExpression(),
                sched.getTriggerState(triggerKey).name());
    }

    /**
     * 修改某个任务的执行时间
     *
     * @param name
     * @param time
     * @return
     * @throws SchedulerException
     */
    public static boolean modifyJob(Scheduler sched,String name, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, JOB_GROUP_NAME);
        CronTrigger cronTrigger = (CronTrigger) sched.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, JOB_GROUP_NAME)
                    .withSchedule(cronScheduleBuilder).build();
            date = sched.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }
}
