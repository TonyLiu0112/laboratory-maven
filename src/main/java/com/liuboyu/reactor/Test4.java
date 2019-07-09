package com.liuboyu.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Test4 {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 100; i++) {
                fluxSink.next(i);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).publish().autoConnect().parallel(5).runOn(Schedulers.elastic()).subscribe(obj -> {
            try {
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread().getName() + ": " + obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
