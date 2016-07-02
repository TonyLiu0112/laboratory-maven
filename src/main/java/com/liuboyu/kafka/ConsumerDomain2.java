package com.liuboyu.kafka;

import com.liuboyu.kafka.consumer.Consumer;

public class ConsumerDomain2 {
	public static void main(String[] args) {
		
		Consumer consumer = new Consumer("test", "group2");
		
		new Thread(consumer).start();
		
	}
}
