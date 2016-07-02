## AMQP
AMQP，即Advanced Message Queuing Protocol,一个提供统一消息服务的应用层标准高级消息队列协议,是应用层协议的一个开放标准,为面向消息的中间件设计。
基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同开发语言等条件的限制。

## Overview

This is an example of how use the Java JMS api with ActiveMQ via the AMQP protocol.

## Prereqs

- Install Java SDK
- Install [Maven](http://maven.apache.org/download.html) 

## Building

Run:

    mvn install

## Running the Examples

In one terminal window run:

    java -cp target/amqp-example-0.1-SNAPSHOT.jar example.Listener

In another terminal window run:

    java -cp target/amqp-example-0.1-SNAPSHOT.jar example.Publisher

You can control to which AMQP server the examples try to connect to by
setting the following environment variables: 

* `ACTIVEMQ_HOST`
* `ACTIVEMQ_PORT`
* `ACTIVEMQ_USER`
* `ACTIVEMQ_PASSWORD`