package com.liuboyu.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class LoadingCacheTest {

    private static LoadingCache<Long, AtomicLong> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .removalListener(notification -> {

            })
            .build(new CacheLoader<Long, AtomicLong>() {

                @Override
                public AtomicLong load(Long key) {
                    return new AtomicLong(0);
                }
            });


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long limit = 1000L;
        while (true) {

        }


    }

}
