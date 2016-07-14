package com.liuboyu.kafka.consumer;

import com.kiisoo.aegis.common.rawdata.RawDataRecord;
import com.kiisoo.aegis.common.rawdata.protocol.alinket.ParserException;
import com.kiisoo.aegis.common.rawdata.protocol.alinket.report.WifiRawShortDataParser;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Consumer implements Runnable {

    public ConsumerConnector consumer;
    public String topic;

    private int count;

    public Consumer(String topic, String group) {
        consumer = kafka.consumer.Consumer
                .createJavaConsumerConnector(createConsumer(group));
        this.topic = topic;
    }

    public ConsumerConfig createConsumer(String group) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.0.212:2181");
        props.put("group.id", group);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        // 创建消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> kafkaMap = consumer
                .createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> kafkaStream = kafkaMap.get(topic).get(0);

        ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
        // 解析消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WifiRawShortDataParser wifiRawShortDataParser = new WifiRawShortDataParser();
        while (iterator.hasNext()) {
            MessageAndMetadata<byte[], byte[]> message = iterator.next();
            try {

                RawDataRecord record = wifiRawShortDataParser.parse(message.message());
//                System.out.println(String.format("mac[%s], smac[%s], time[%s]", record.getDmac(), record.getSmac(), sdf.format(new Date(record.getMaxstamp()))));
                if (record.getSmac().equals("cdcf4371fa45")) {
                    count++;
                    System.out.println("cdcf4371fa45数据条数 " + count);
                }
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }

    }

}
