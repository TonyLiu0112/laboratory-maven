package com.liuboyu.kafka;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

public class OldProducerTest {

    public static void main(String[] args) throws InterruptedException {
        javaapi();
    }

    private static void clients() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "qs-pro-01:9092,qs-pro-03:9092,qs-pro-04:9092");
        properties.put("acks", "0");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("partitioner.class", "com.kiisoo.commons.component.kafka.test.RandomPartitioner");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(properties);
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String msg = "Message " + new Date();
                Future<RecordMetadata> record = producer.send(new ProducerRecord<>("topic_tony_test", "123", msg));
                System.out.println(msg + " " + record.get().partition());
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private static void javaapi() throws InterruptedException {
        Properties props = new Properties();
        props.put("metadata.broker.list", "qs-pro-01:9092,qs-pro-03:9092,qs-pro-04:9092,qs-pro-05:9092,qs-pro-06:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("val.serializer.class", "kafka.serializer.StringEncoder");
        // 设置Partition类, 对队列进行合理的划分
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");
        //props.put("num.partitions", "4");

        // ACK机制, 消息发送需要kafka服务端确认
        //props.put("request.required.acks", "1");

        kafka.javaapi.producer.Producer<String, String> javaapiProducer = new kafka.javaapi.producer.Producer<>(new ProducerConfig(props));

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            javaapiProducer.send(new KeyedMessage<>("wifi-raw-data", "ddd"));
            Thread.sleep(1000L);
        }
    }

}
