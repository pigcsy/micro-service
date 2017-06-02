package com.storm.support.utils;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import storm.kafka.KafkaUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class KafkaUtil extends KafkaUtils {


    public static ConsumerConnector createConsumerConnector(Properties properties) {
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        return Consumer.createJavaConsumerConnector(consumerConfig);
    }


    public static KafkaStream<String, String> getTopicStream(String topic, ConsumerConnector connector) {
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, 1);
        StringDecoder decoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> map = connector.createMessageStreams(topicCount, decoder, decoder);
        KafkaStream<String, String> stream = map.get(topic).get(0);
        return stream;
    }


    public static List<KafkaStream<String, String>> getTopicStreames(String topic, int count, ConsumerConnector connector) {
        Map<String, Integer> topicCount = new HashMap<String, Integer>();
        topicCount.put(topic, count);
        StringDecoder decoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> map = connector.createMessageStreams(topicCount, decoder, decoder);
        return map.get(topic);
    }
}
