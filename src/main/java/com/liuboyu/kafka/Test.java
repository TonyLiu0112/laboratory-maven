package com.liuboyu.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        apacheClient();
    }

    private static void apacheClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "qs-stg-02:9092,qs-stg-03:9092,qs-stg-09:9092");
//        properties.put("bootstrap.servers", "qs-pro-01:9092,qs-pro-04:9092,qs-pro-05:9092,qs-pro-06:9092");
        properties.put("acks", "0");
//        properties.put("retries", 0);
//        properties.put("batch.size", 16384);
//        properties.put("linger.ms", 1);
//        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("partitioner.class", "com.liuboyu.kafka.MyPartitioner");
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(properties);
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String msg = "Message " + new Date();
                producer.send(new ProducerRecord<>("tony_test", 0, "123", msg));
                producer.send(new ProducerRecord<>("tony_test", 1, "456", msg));
                producer.send(new ProducerRecord<>("tony_test", 2, "789", msg));
                Thread.sleep(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
