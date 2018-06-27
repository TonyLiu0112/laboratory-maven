package com.liuboyu.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;

import java.io.IOException;

public class MessageProvider {

    public static void main(String[] args) throws Exception {
        // advisor_exchange_customercomment
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("www.tony666.com");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("advisor_queue_work_customercomment", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        });
    }

}
