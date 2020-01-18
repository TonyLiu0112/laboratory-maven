package com.liuboyu.datastructure;

import lombok.Getter;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 *
 * @author Tony
 */
public class DelayQueueCase {

    public static void main(String[] args) throws Exception {
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        delayQueue.offer(new Message(20000L, "我是延时20秒的消息"));
        delayQueue.offer(new Message(2000L, "我是延时2秒的消息"));
        delayQueue.offer(new Message(10000L, "我是延时10秒的消息"));

        System.out.println("准备开始获取消息...");
        Thread.sleep(2000L);

        int finished = 0;
        do {
            Message message = delayQueue.take();
            System.out.println(message.toString());
            finished++;
        } while (finished < 3);

    }

    @Getter
    private static class Message implements Delayed {

        // nano
        private final long execTime;
        private final String body;

        private Message(long delayMs, String body) {
            this.execTime = TimeUnit.NANOSECONDS.convert(delayMs, TimeUnit.MILLISECONDS) + System.nanoTime();
            this.body = body;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.toNanos(execTime - System.nanoTime());
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "Message{" +
                    "execTime=" + execTime +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

}
