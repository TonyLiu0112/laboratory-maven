package com.liuboyu.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class Example {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        //noinspection unchecked
        Flux.create(fluxSink -> {
            fluxSink.next(new MyTask());
            fluxSink.next(new MyTask());
            fluxSink.complete();
        }).parallel(2).runOn(Schedulers.elastic()).subscribe(o -> Example.printResult(executorService.submit((Callable<String>) o)));
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void printResult(Object o) {
        //noinspection unchecked
        Future<String> future = (Future<String>) o;
        try {
            log.info("task result is " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("task start now. {}", Thread.currentThread().getName());
            Thread.sleep((new Random().nextInt(10) + 5) * 1000);
            return Thread.currentThread().getName();
        }
    }

}
