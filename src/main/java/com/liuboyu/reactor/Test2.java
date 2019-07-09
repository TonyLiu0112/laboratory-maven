package com.liuboyu.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Flux.create(fluxSink -> {
            for (int i = 1; i <= 10000000; i++) {
                fluxSink.next(i);
            }
//            fluxSink.complete();
        }).parallel(5).runOn(Schedulers.elastic()).subscribe(i -> say(Integer.parseInt(i.toString())));
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static String say(int i) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " say" + i);
        return "say " + i;
    }

    private static void test3() {
        Mono.just("Tony").subscribe();
    }

}
