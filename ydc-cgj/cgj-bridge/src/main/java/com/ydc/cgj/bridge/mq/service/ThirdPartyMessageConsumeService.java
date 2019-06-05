package com.ydc.cgj.bridge.mq.service;

/**
 * 消息消费service
 */
public interface ThirdPartyMessageConsumeService {

    /**
     * 消费消息
     * @param content
     * @return
     */
    boolean consumeMessage(byte[] content, final boolean isRetry);
}
