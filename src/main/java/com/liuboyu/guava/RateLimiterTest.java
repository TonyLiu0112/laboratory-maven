package com.liuboyu.guava;


import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    public static void main(String[] args) {
        // 令牌桶算法
        RateLimiter rateLimiter = RateLimiter.create(5);
        System.out.println(rateLimiter.toString());
        System.out.println(rateLimiter.acquire(10));
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.acquire());
        System.out.println(rateLimiter.tryAcquire());
        System.out.println(rateLimiter.acquire());

        System.out.println(" -------------------------- ");

        // 漏桶算法
        RateLimiter limiter = RateLimiter.create(5, 1, TimeUnit.SECONDS);
        for (int i = 0; i < 10; i++) {
            System.out.println(limiter.acquire());
        }

    }

}
