package com.liuboyu.cache;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class ExpirationTest {

    public static void main(String[] args) throws InterruptedException {
        ExpiringMap<String, String> map = ExpiringMap.builder()
                .maxSize(10)
                .expiration(1, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .variableExpiration()
                .build();

        map.put("liu1", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu2", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu3", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu4", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu5", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu6", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu7", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu8", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu9", "aaaaa", 1000, TimeUnit.SECONDS);
        map.put("liu10", "aaaaa", 1000, TimeUnit.SECONDS);

        System.out.println("准备第11个");
        String put = map.put("liu11", "aaaaa", 1000, TimeUnit.SECONDS);
        System.out.println("11个结果 " + put);

        System.out.println(map.toString());
    }

}
