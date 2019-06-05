package com.ydc.service.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ydc.dao.cgj.common.SysErrorLogRocketmqDao;
import com.ydc.model.cgj.SysErrorLogRocketmq;
import com.ydc.service.user.service.SysErrorLogRocketmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @create 2018-11-13 13:41
 **/
@Service
public class SysErrorLogRocketmqServiceImpl implements SysErrorLogRocketmqService {

    @Autowired
    SysErrorLogRocketmqDao sysErrorLogRocketmqDao;

    @Override
    public int insert(SysErrorLogRocketmq record) {
        return sysErrorLogRocketmqDao.insert(record);
    }

    @Override
    public void consumeFailure(String contentString, String topic, String tag, Integer delayTimeLevel) {
        SysErrorLogRocketmq sysErrorLogRocketmq = new SysErrorLogRocketmq();
        sysErrorLogRocketmq.setContent(contentString);
        sysErrorLogRocketmq.setTopic(topic);
        sysErrorLogRocketmq.setTag(tag);
        sysErrorLogRocketmq.setDelayTimeLevel(delayTimeLevel);
        sysErrorLogRocketmq.setRepetitionNum(3);
        sysErrorLogRocketmq.setSendNum(0);
        sysErrorLogRocketmq.setStatus(0);
        insert(sysErrorLogRocketmq);
    }
}
