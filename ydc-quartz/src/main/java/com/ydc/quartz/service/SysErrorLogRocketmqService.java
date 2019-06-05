package com.ydc.quartz.service;

import com.ydc.beans.mq.common.RepetitionMQService;
import com.ydc.commom.util.JsonUtil;
import com.ydc.dao.cgj.common.SysErrorLogRocketmqDao;
import com.ydc.model.cgj.SysErrorLogRocketmq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * MQ消息处理
 *
 * @author
 * @create 2018-11-12 18:42
 **/
@Service("sysErrorLogRocketmqService")
public class SysErrorLogRocketmqService {

    private static final Logger logger = LogManager.getLogger(SysErrorLogRocketmqService.class);

    @Autowired
    SysErrorLogRocketmqDao sysErrorLogRocketmqDao;
    @Autowired
    RepetitionMQService repetitionMQService;

    public List<SysErrorLogRocketmq> getPendingSysErrorLogRockeMq(){
        return sysErrorLogRocketmqDao.getPendingSysErrorLogRockeMq();
    }

    public int updateSendNum(Integer id){
        return sysErrorLogRocketmqDao.updateSendNum(id);
    }

    public int updateSucceed(Integer id){
        return sysErrorLogRocketmqDao.updateSucceed(id);
    }

    /**
     * 消息提供成功
     * @param id
     */
    public void consumeSuccess(Integer id){
        updateSucceed(id);
    }

    /**
     * 处理消息提供失败MQ
     */
    public void job(){
        List<SysErrorLogRocketmq> logRocketmqs = getPendingSysErrorLogRockeMq();
        logger.info("subject:{},size:{},logRocketmqs:{}","需要处理的消息",(logRocketmqs == null || logRocketmqs.isEmpty() ? 0 : logRocketmqs.size()),JsonUtil.gsonStr(logRocketmqs));
        if(logRocketmqs == null || logRocketmqs.isEmpty())return;
        logRocketmqs.forEach(item ->{
            try{
                repetitionMQService.repetitionManage(item.getId(),item.getTopic(),item.getTag(),item.getDelayTimeLevel(),item.getContent());
                consumeSuccess(item.getId());
            }catch (Exception e){
                logger.error("subject:{},id:{};topic:{};tag:{};delayTimeLevel:{};content:{},e:{}","MQ重新消费",item.getId(),item.getTopic(),item.getTag(),item.getDelayTimeLevel(),item.getContent(),e);
                updateSendNum(item.getId());
            }
        });
    }
}
