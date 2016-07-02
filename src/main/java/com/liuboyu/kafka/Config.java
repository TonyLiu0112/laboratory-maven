package com.liuboyu.kafka;

import kafka.javaapi.producer.Producer;

/**
 * kafka 配置信息
 * @author liuboyu
 *
 */
public interface Config {
	
	static final String ZOOKEEPER = "127.0.0.1:2181";
	
	static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
	
	static final String KAFKA_SERVER = "127.0.0.1:9092";
	
	public Producer<String, byte[]> init(String zk);
}
