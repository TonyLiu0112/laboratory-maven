package com.liuboyu.reactor;

import reactor.core.publisher.Flux;

public class Test3 {

    public static void main(String[] args) {
        Flux.just(1, 2, 3).filter(n -> n % 2 == 0).subscribe(System.out::println);
    }

}
