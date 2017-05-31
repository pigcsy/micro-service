package com.mq.support.mq.tcp;/**
 * Created by maven on 2017/3/2.
 */

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderAction;
import com.aliyun.openservices.ons.api.order.OrderConsumer;
import com.core.utils.JsonUtil;

import java.util.Properties;

/**
 * mq tcp 接收消息
 *
 * @author Maven
 */
public class TcpConsumer {


    public boolean receive(Entity entity) {
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.ConsumerId, MqConfig.consumerId);
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, MqConfig.accessKey);
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, MqConfig.secretKey);
        consumerProperties.setProperty(PropertyKeyConst.ONSAddr, MqConfig.ONSADDR);
        OrderConsumer consumer = ONSFactory.createOrderedConsumer(consumerProperties);
        consumer.subscribe(entity.getTopic(), entity.getTag(), new MessageOrderListener() {
            @Override
            public OrderAction consume(final Message message, final ConsumeOrderContext context) {
                JsonUtil jsonUtils = new JsonUtil();
                System.out.println(jsonUtils.byte2Object(message.getBody()).toString());
                return OrderAction.Success;
            }
        });
        consumer.start();
        System.out.println("Consumer start success.");

        //等待固定时间防止进程退出
        try {
            Thread.sleep(200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }


}
