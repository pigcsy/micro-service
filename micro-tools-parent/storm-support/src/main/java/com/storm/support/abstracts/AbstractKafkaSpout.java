package com.storm.support.abstracts;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.support.utils.KafkaUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractKafkaSpout<T> extends AbstractBaseSpout {

    private static final long serialVersionUID = 1L;
    protected ConsumerConnector connector;
    private ObjectMapper objectMapper;

    @Override
    @SuppressWarnings("rawtypes")
    public void openInternal(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        Properties props = getKafkaConsumerConfig();
        connector = KafkaUtil.createConsumerConnector(props);
        objectMapper = new ObjectMapper();
    }

    @Override
    public void nextTuple() {
        try {
            KafkaStream<String, String> stream = KafkaUtil.getTopicStream(getTopic(), connector);
            ConsumerIterator<String, String> it = stream.iterator();
            while (it.hasNext()) {
                MessageAndMetadata<String, String> msg = it.next();
                String json = msg.message();
                logger.info("\n\n----------Spout收到消息: " + json);
                T t = parse(json);
                if (t != null) {
                    handle(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 子类可重写日志解析方法，默认为json格式解析
     *
     * @param json
     * @return
     */
    public T parse(String json) {
        T t = null;
        try {
            t = objectMapper.readValue(json, assignTranslatedClass());
            logger.info("----------Spout fetch: " + json);
        } catch (Exception e) {
            logger.error("从JSON反序列化DTO异常, json=" + json, e);
            t = null;
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    protected Class<T> assignTranslatedClass() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
            if (types != null && types.length > 0) {
                return (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
            }
        }
        throw new RuntimeException(getClass().getName() + "必须指定父类"
                + getClass().getSuperclass().getSimpleName() + "的泛型");
    }

    public abstract void handle(T dto);

    public abstract Properties getKafkaConsumerConfig();

    public abstract String getTopic();
}
