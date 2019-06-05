package com.ydc.service.user.service;

import com.ydc.model.cgj.SysErrorLogRocketmq;

/**
 * @author
 * @create 2018-11-13 13:40
 **/
public interface SysErrorLogRocketmqService {

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(SysErrorLogRocketmq record);


    /**
     * 消费失败
     * @param contentString
     * @param topic
     * @param tag
     * @param delayTimeLevel
     */
    void consumeFailure(String contentString, String topic, String tag, Integer delayTimeLevel);
}
