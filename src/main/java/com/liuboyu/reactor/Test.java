package com.liuboyu.reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class Test {

    public static void main(String[] args) throws Exception {

        // simple
        Flux.just("Hello", "world").subscribe(Test::process);
        System.out.println();
        // range
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        System.out.println();
        // zip
        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"))
                .subscribe(Test::process);
        System.out.println();
        // delay
        Disposable stringFlux = Mono.just("Hello").concatWith(Mono.just("world"))
                .delayElements(Duration.ofMillis(1000)).subscribe(System.out::println); //.delaySubscription(Duration.ofMillis(1));
//        stringFlux.dispose();
//        stringFlux.subscribe(System.out::println);
//        stringFlux.toStream().forEach(System.out::println);
        stringFlux.dispose();
        System.out.println();

        // message
        Flux.just(1, 2)
                .concatWith(Mono.error(new NullPointerException("出错了")))
                .retry(1)
                .subscribe(System.out::println, System.err::println, () -> System.out.println("完成了"));
        System.out.println();

        // 调度器
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] +%s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] +%s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }

    private static void process(Tuple2<String, String> tuple2) {
        System.out.println(tuple2.toString());
    }

    private static void process(String msg) {
        if (msg.equals("Hello")) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<" + msg + ">");
    }

}
