/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ydc.service.order.mq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

public class Consumer {
    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws MQClientException {
       // DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQTestConifg.consumer_group+Long.toString(System.currentTimeMillis()));
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQTestConifg.consumer_group);
        consumer.setInstanceName(RocketMQTestConifg.consumer_instane+""+new Random().nextInt(20));
        consumer.setVipChannelEnabled(false);
        consumer.setNamesrvAddr(RocketMQTestConifg.post);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(RocketMQTestConifg.topic, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
               logger.info("----------------");
                for (MessageExt messageExt:msgs){
                    try {
                        String msgBody=new String(messageExt.getBody(),"utf-8");
                       logger.info("  ---------"+msgBody);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        System.out.printf("失败哦");
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
               // System.out.printf("New Messages: %s %n", msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
