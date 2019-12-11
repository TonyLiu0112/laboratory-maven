package com.liuboyu.kafka.consumer;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.*;

public class Consumer implements Runnable {

    private ConsumerConnector consumer;
    private String topic;

    public Consumer(String topic, String group) {
        consumer = kafka.consumer.Consumer
                .createJavaConsumerConnector(createConsumer(group));
        this.topic = topic;
    }

    private ConsumerConfig createConsumer(String group) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "10.211.55.20:2181");
        props.put("group.id", group);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, 1);
        // 创建消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> kafkaMap = consumer
                .createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> kafkaStream = kafkaMap.get(topic).get(0);

        // 解析消息
        for (MessageAndMetadata<byte[], byte[]> message : kafkaStream) {
            System.out.println(Arrays.toString(message.message()));
        }

    }

}
