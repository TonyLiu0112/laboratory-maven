package com.liuboyu.redis.zip;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashSet;
import java.util.Set;

public class ShardTest {

    public static void main(String[] args) {
        test2();
        /*
        4695024766523915970
        4695024766523915970
        4695024766523915970
        4695024766523915970
        4695024766523915970
         */
    }

    private static void test1() {
        Set<Long> set = new HashSet<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            set.add(CRC64Util.hashByAlgo2(("Tony666" + i).getBytes()));
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("耗时: " + end);
        System.out.println(set.size());
    }

    private static void test2() {
        long x = CRC64Util.hashByAlgo2(("Tony666").getBytes());
        System.out.println(x);
        System.out.println(x % 200);
        System.out.println(x / 500);
    }

    private static long getErpShopShardId(String erpShop) {
        StringBuilder sb = new StringBuilder();
        for (char c : erpShop.toCharArray()) {
            System.out.println((int) c);
            sb.append((int) c);
        }
        return Long.parseLong(sb.toString());
    }

    private static long crc64(String str) {
        System.out.println(CRC64Util.hashByAlgo1("Tony666".getBytes()));
        System.out.println(CRC64Util.hashByAlgo2("Tony666".getBytes()));
        return 0l;
    }

}
