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

import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static void main(String[] args)  throws MQClientException, InterruptedException {
            DefaultMQProducer producer = new DefaultMQProducer(RocketMQTestConifg.broker_group);
            producer.setNamesrvAddr(RocketMQTestConifg.post);
            producer.setVipChannelEnabled(false);
            producer.setInstanceName(RocketMQTestConifg.broker_instane);
           // producer.setSendMsgTimeout(20000);
            producer.start();
                StopWatch stop = new StopWatch();
                stop.start();
        for (int i = 0; i < 10; i++) {
            try {

                Message msg = new Message(RocketMQTestConifg.topic,
                        "TagA",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );

                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf("发送失败-----");
               // Thread.sleep(1000);
            }
        }
        stop.stop();
        System.out.printf("发送陈宫-----");
        producer.shutdown();
    }
}
