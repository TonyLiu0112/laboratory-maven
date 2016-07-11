package com.liuboyu.kafka;

import com.liuboyu.kafka.producer.Procuder;

public class ProducerDomain {

    static final String TOPIC_NAME = "wifi-raw-data-rs";

    public static void main(String[] args) {
        Procuder procuder = new Procuder();
//		for (int i = 0; i < 100; i ++) {
//			procuder.doSend(TOPIC_NAME, "message from java app " + i);
//		}
        for (int i = 0; i < 10000; i++) {
            Metadata metadata = new Metadata();
            metadata.setMac("000000000000");
            metadata.setMaxRssi(-99);
            metadata.setMaxRssi((int) (System.currentTimeMillis()/1000));
            metadata.setMinRssi(-10);
            metadata.setMinTimestamp((int) (System.currentTimeMillis()/1000));
            metadata.setsMac("CDCF4371FA45");
            procuder.doSend(TOPIC_NAME, ConvertUtil.convert2bytes(metadata).get());
            System.out.println("message send success!");
        }

    }
}
