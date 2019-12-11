package com.liuboyu.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ZookeeperConsumerConnector;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class TestConsumer {

    public static void main(String[] args) throws InterruptedException {

        kafkaApi();
    }

    private static void kafkaApi() {
        Properties originalProps = new Properties();

        originalProps.put("zookeeper.connect", "qs-stg-02:2181,qs-stg-03:2181,qs-stg-06:2181,qs-stg-07:2181,qs-stg-09:2181");
//        originalProps.put("zookeeper.connect", "qs-pro-01:2181,qs-pro-03:2181,qs-pro-04:2181,qs-pro-05:2181,qs-pro-06:2181");

        originalProps.put("group.id", "tony-group");
        originalProps.put("zookeeper.session.timeout.ms", "10000");
        originalProps.put("zookeeper.sync.time.ms", "200");
        originalProps.put("auto.commit.enable", "false");
        originalProps.put("auto.commit.interval.ms", "1000");
        originalProps.put("auto.offset.reset", "smallest");
        originalProps.put("serializer.class", "kafka.serializer.StringEncoder");

        ZookeeperConsumerConnector consumer = (ZookeeperConsumerConnector) kafka.consumer.Consumer.createJavaConsumerConnector(new ConsumerConfig(originalProps));

        Map<String, Integer> topicMap = new HashMap<>();
        topicMap.put("tony1", 1);

        Decoder<String> keyDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());
        Decoder<String> valueDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> messageStreams = consumer.createMessageStreams(topicMap, keyDecoder, valueDecoder);
        List<KafkaStream<String, String>> kafkaStreams = messageStreams.get("tony1");

        kafkaStreams.forEach(kafkaStream -> kafkaStream.forEach(messageAndMetadata -> {
            System.out.println(messageAndMetadata.message() + " " + messageAndMetadata.partition());
            consumer.commitOffsets();
        }));
    }

    private static void apacheClient() throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "qs-stg-02:9092,qs-stg-03:9092,qs-stg-09:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "tony666");
        properties.put("partition.assignment.strategy", "range");
//        properties.put("auto.offset.reset", "smallest");
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
//        consumer.subscribe(new TopicPartition("tony1", 0));
        consumer.subscribe("tony1");

        for (; ; ) {
            Map<String, ConsumerRecords<String, String>> messageMap = consumer.poll(9L);
            if (messageMap == null || messageMap.size() == 0) {
                Thread.sleep(1000L);
                continue;
            }
            for (Map.Entry<String, ConsumerRecords<String, String>> consumerRecordsEntry : messageMap.entrySet()) {
                System.out.println(consumerRecordsEntry.getKey());
                System.out.println(consumerRecordsEntry.getValue());
                System.out.println("-------");
            }
        }
    }

}
