package com.liuboyu.guava.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Testing with guava event bus.
 *
 * @author Tony
 */
public class EventBusTest {

    private static EventBus eventBus = new EventBus();

    public static void main(String[] args) throws InterruptedException {
        MyListener.ListenerA listenerA = new MyListener.ListenerA();
        MyListener.ListenerB listenerB = new MyListener.ListenerB();
        eventBus.register(listenerA);
        eventBus.register(listenerB);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                eventBus.post("Tony666-" + System.currentTimeMillis());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(() -> {
            for (int i = 0; i < 100; i++) {
                eventBus.post(new MyListener.DogEvent("Jams-" + System.currentTimeMillis()));
                try {
                    Thread.sleep(1100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread.sleep(Long.MAX_VALUE);
    }

    private static class MyListener {

        @Slf4j
        private static class ListenerA {

            @Subscribe
            public void stringEvent(String event) {
                log.info("ListenerA now get event. {}", event);
            }
        }

        @Slf4j
        private static class ListenerB {

            @Subscribe
            public void dogEvent(DogEvent dogEvent) {
                log.info("ListenerB now get event. {}", dogEvent);
            }

        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        private static class DogEvent {
            private String name;
        }

    }

}
