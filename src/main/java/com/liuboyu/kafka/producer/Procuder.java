package com.liuboyu.kafka.producer;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import com.liuboyu.kafka.config.KafkaConfig;
import kafka.producer.ProducerConfig;

import java.util.Properties;

import static com.liuboyu.kafka.Config.KAFKA_SERVER;
import static com.liuboyu.kafka.Config.SERIALIZER_CLASS;
import static com.liuboyu.kafka.Config.ZOOKEEPER;

public class Procuder {
	
	private KafkaConfig config = new KafkaConfig();
	private Producer<String, byte[]> producer = null;

	public Procuder() {
        Properties properties = new Properties();
        properties.put("zk.connect", "192.168.0.216:2181");
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list", "192.168.0.211:9092,192.168.0.212:9092,192.168.0.213:9092");

        ProducerConfig config = new ProducerConfig(properties);
		producer = new Producer<>(config);
	}
	
	/**
	 * send message to kafka
	 */
	public void doSend(String topicName, byte[] message) {
		KeyedMessage<String, byte[]> keyedMsg = new KeyedMessage<>(topicName, message);
		producer.send(keyedMsg);
	}
	
}
