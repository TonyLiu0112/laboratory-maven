package com.liuboyu.kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ZookeeperConsumerConnector;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class KafkaConsumerExample {

    public static void main(String[] args) {

        PartitionerConsumer partitionerConsumer = new PartitionerConsumer("tony_test", 3, 0);
        partitionerConsumer.doConsumer();

    }

    private static class PartitionerConsumer {
        private final String topic;
        private final int partitions;
        private final int currentPartition;

        private PartitionerConsumer(String topic, int partitions, int currentPartition) {
            this.topic = topic;
            this.currentPartition = currentPartition;
            this.partitions = partitions;
        }

        private void doConsumer() {
            Properties originalProps = new Properties();
            originalProps.put("zookeeper.connect", "qs-stg-02:2181,qs-stg-03:2181,qs-stg-06:2181,qs-stg-07:2181,qs-stg-09:2181");
            originalProps.put("group.id", "tony-group");
            originalProps.put("zookeeper.session.timeout.ms", "10000");
            originalProps.put("zookeeper.sync.time.ms", "200");
            originalProps.put("auto.commit.enable", "true");
            originalProps.put("auto.commit.interval.ms", "1000");
            originalProps.put("auto.offset.reset", "smallest");
            originalProps.put("serializer.class", "kafka.serializer.StringEncoder");
            originalProps.put("partition.assignment.strategy", "roundrobin");

            ZookeeperConsumerConnector consumer = (ZookeeperConsumerConnector) kafka.consumer.Consumer.createJavaConsumerConnector(new ConsumerConfig(originalProps));
            Map<String, Integer> topicMap = new HashMap<>();
            topicMap.put(topic, 1);
            Decoder<String> keyDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());
            Decoder<String> valueDecoder = new kafka.serializer.StringDecoder(new VerifiableProperties());

            List<KafkaStream<String, String>> kafkaStreams = consumer.createMessageStreams(topicMap, keyDecoder, valueDecoder).get(topic);

            ExecutorService consumerPools = Executors.newFixedThreadPool(3);
            kafkaStreams.forEach(kafkaStream -> consumerPools.submit(new KafkaStreamRunnable(kafkaStream, currentPartition)));

//            kafkaStreams.forEach(kafkaStream -> kafkaStream.forEach(messageAndMetadata -> {
//                log.info(Thread.currentThread().getName() + ": " + messageAndMetadata.message() + " " + messageAndMetadata.partition());
//            }));
        }

    }

    private static class KafkaStreamRunnable implements Runnable {

        private final KafkaStream<String, String> kafkaStream;
        private final int currentPartition;

        private KafkaStreamRunnable(KafkaStream<String, String> kafkaStream, int currentPartition) {
            this.kafkaStream = kafkaStream;
            this.currentPartition = currentPartition;
        }

        @Override
        public void run() {
            AtomicInteger streamPartition = new AtomicInteger();
            kafkaStream.forEach(messageAndMetadata -> {
                if (messageAndMetadata.partition() != currentPartition) {
                    log.info(messageAndMetadata.partition() + "");
                }
                log.info(Thread.currentThread().getName() + ": " + messageAndMetadata.message() + " " + messageAndMetadata.partition());
            });
            log.info("{} it's not target partition {}, ignore.", streamPartition.get(), currentPartition);
        }
    }

}
