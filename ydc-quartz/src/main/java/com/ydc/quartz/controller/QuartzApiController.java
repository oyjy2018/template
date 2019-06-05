package com.ydc.quartz.controller;

import com.ydc.beans.redis.RedisUtil;
import com.ydc.commom.constant.RedisConstant;
import com.ydc.quartz.config.QuartzScheduler;
import com.ydc.quartz.service.init.InitCache;
import com.ydc.quartz.service.init.InitRSAKey;
import com.ydc.quartz.service.init.InitScheduler;
import com.ydc.quartz.task.AutoNotarizeReceivingJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务
 *
 * @author gongjin
 * @create 2018-09-10 13:59
 **/
@RestController
public class QuartzApiController {

    @Autowired
    InitScheduler initScheduler;

    @Autowired
    Scheduler scheduler;

    @Autowired
    InitCache initCache;

    @Autowired
    InitRSAKey initRSAKey;


    /**
     * 手动初始化缓存
     * @return
     */
    @GetMapping(value = "/initRun")
    public String initRun(){
        return initCache.initRun();
    }

    /**
     * 重置秘钥
     * @return
     */
    @GetMapping(value = "/initSecretKey")
    public String initSecretKey(){
        return initRSAKey.init();
    }





    @GetMapping("/start")
    public void startQuartzJob() {
        QuartzScheduler.addJob(scheduler,"autoNotarizeReceivingJob",AutoNotarizeReceivingJob.class,"0/2 * * * * ?");
    }

    @GetMapping("/pauseJob")
    public void pauseJob() {
        try {
            QuartzScheduler.pauseJob(scheduler,"autoNotarizeReceivingJob");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/pauseJob/{taskId}")
    public void pauseJob(@PathVariable(value = "taskId") String taskId){
        try{
            QuartzScheduler.pauseJob(scheduler,taskId);
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/pauseAllJob")
    public void pauseAllJob() {
        try {
            QuartzScheduler.pauseAllJob(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/resumeJob/{taskId}")
    public void resumeJob(@PathVariable(value = "taskId") String taskId) {
        try {
            QuartzScheduler.resumeJob(scheduler,taskId);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/resumeJob")
    public void resumeJob() {
        try {
            QuartzScheduler.resumeJob(scheduler,"autoNotarizeReceivingJob");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



    @GetMapping("/resumeAllJob")
    public void resumeAllJob() {
        try {
            QuartzScheduler.resumeAllJob(scheduler);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/getJobInfo")
    public String getJobInfo() {
        try {
            return QuartzScheduler.getJobInfo(scheduler,"autoNotarizeReceivingJob");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/modifyJob")
    public void modifyJob() {
        try {
            QuartzScheduler.modifyJob(scheduler,"autoNotarizeReceivingJob","0/5 * * * * ?");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/modifyJob/{taskId}")
    public void modifyJob(@PathVariable(value = "taskId") String taskId, @RequestParam(value = "Cron") String cron) {
        try {
            QuartzScheduler.modifyJob(scheduler,taskId,cron);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/deleteJob")
    public void deleteJob() {
        try {
            QuartzScheduler.deleteJob(scheduler,"autoNotarizeReceivingJob");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/checkTaskJobTime")
    public String checkTaskJobTime(){
        return QuartzScheduler.checkTaskJobTime(scheduler,"autoNotarizeReceivingJob","0/2 * * * * ?").toString();
    }

    @GetMapping("/checkTaskJob")
    public String checkTaskJob(){
        return QuartzScheduler.checkTaskJob(scheduler,"autoNotarizeReceivingJob")+"";
    }

    @PostMapping("/setRedis")
    public String setRedis(@RequestParam(value = "hk") String hk,@RequestParam(value = "hv") String hv){
        RedisUtil.redisSet(hk,hv,null);
//        RedisUtil.getRedisTemplate().opsForList().leftPush("USER:TOKEN",hv);
//        RedisUtil.getRedisTemplate().opsForHash().put("USER:TOKEN:AUTH",hk,hv);
        return "成功";
    }

    @PostMapping("/getRedis")
    public String getRedis(@RequestParam(value = "hk") String hk){
        Object object =  RedisUtil.getRedisTemplate().opsForHash().get("USER:TOKEN:AUTH",hk);
        return object.toString();
    }

    @PostMapping("/delRedis")
    public String delRedis(@RequestParam(value = "keyStart") String keyStart,@RequestParam(value = "keyEnd") String keyEnd){
//       RedisUtil.remove("ceshi");
        RedisUtil.getRedisTemplate().delete(RedisUtil.getRedisTemplate().keys(keyStart+"*"+keyEnd));
//        boolean flag = RedisUtil.getRedisTemplate().delete("USER:TOKEN:AUTH");
        return "成功";
    }


}