package com.liuboyu.kafka;

import com.liuboyu.kafka.producer.Procuder;

public class ProducerDomain {
	
	static final String TOPIC_NAME = "spark-topic";
	
	public static void main(String[] args) {
		Procuder procuder = new Procuder();
//		for (int i = 0; i < 100; i ++) {
//			procuder.doSend(TOPIC_NAME, "message from java app " + i);
//		}
		procuder.doSend(TOPIC_NAME, "liuboob".getBytes());
		System.out.println("message send success!");
	}
}
