package com.liuboyu.kafka.config;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import com.liuboyu.kafka.Config;

public class KafkaConfig implements Config {
	
	Properties properties = new Properties();

	public Producer<String, byte[]> init(String zk) {
		
		if (zk == null || "".equals(zk))
			zk = ZOOKEEPER;
		
		properties.put("zk.connect", zk);
		properties.put("serializer.class", SERIALIZER_CLASS);
		properties.put("metadata.broker.list", KAFKA_SERVER);
		
		ProducerConfig config = new ProducerConfig(properties);
		return new Producer<>(config);
	}
	
}
