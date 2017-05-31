package com.mq.support.mq.tcp;
/**
 * Created by maven on 2017/3/2.
 */

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.core.utils.JsonUtil;

import java.util.Properties;

/**
 * mq tcp 发送消息（异步）
 *
 * @author Maven
 */
public class TcpProducer {

    public boolean send(Entity entity) {
        Properties producerProperties = new Properties();
        producerProperties.setProperty(PropertyKeyConst.ProducerId, MqConfig.producerId);
        producerProperties.setProperty(PropertyKeyConst.AccessKey, MqConfig.accessKey);
        producerProperties.setProperty(PropertyKeyConst.SecretKey, MqConfig.secretKey);
        producerProperties.setProperty(PropertyKeyConst.ONSAddr, MqConfig.ONSADDR);
        producerProperties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");//设置发送超时时间，单位毫秒
        Producer producer = ONSFactory.createProducer(producerProperties);
        // 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
        producer.start();
        JsonUtil jsonUtils = new JsonUtil();
        // Message message = new Message(MqConfig.topic, MqConfig.TAG, msg.getBytes());
        Message message = new Message(entity.getTopic(), entity.getTag(), jsonUtils.object2Byte(JSON.toJSONString(entity.getBody())));
        // Message Tag,可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
        // Message Body，任何二进制形式的数据，MQ不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
        // 设置代表消息的业务关键属性，请尽可能全局唯一。以方便您在无法正常收到消息情况下，可通过MQ控制台查询消息并补发。
        // 注意：不设置也不会影响消息正常收发
        String orderId = entity.getType();
        message.setKey("orderId");
        message.setBody(JSON.toJSONString(entity.getBody()).getBytes());
        // 异步发送消息, 发送结果通过callback返回给客户端。
        producer.sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(final SendResult sendResult) {
                // 消费发送成功
                System.out.println("send message success. topic=" + sendResult.getTopic() + ", msgId=" + sendResult.getMessageId());
            }

            @Override
            public void onException(OnExceptionContext context) {
                // 消息发送失败
                System.out.println("send message failed. topic=" + context.getTopic() + ", msgId=" + context.getMessageId());
            }
        });
        // 在callback返回之前即可取得msgId。
        System.out.println("send message async. topic=" + message.getTopic() + ", msgId=" + message.getMsgID());
        // 在应用退出前，销毁Producer对象。注意：如果不销毁也没有问题
        producer.shutdown();
        return true;
    }

}
