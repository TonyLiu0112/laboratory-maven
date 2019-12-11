package com.liuboyu.redis;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

public class DoTest {

    private static ShardedJedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
        List<JedisShardInfo> list = new LinkedList<>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public static void main(String[] args) throws Exception {
        testWriter();
    }

    private static void testWriter() throws InterruptedException {
        new Thread(new Worker(pool.getResource(), 1)).start();
//        new Thread(new Worker(pool.getResource(), 200000)).start();
//        new Thread(new Worker(pool.getResource(), 400000)).start();
//        new Thread(new Worker(pool.getResource(), 600000)).start();
//        new Thread(new Worker(pool.getResource(), 800000)).start();
        System.out.println("正在写入...");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class Worker implements Runnable {

        private final ShardedJedis jedis;
        private int begin;

        Worker(ShardedJedis jedis, int begin) {
            this.jedis = jedis;
            this.begin = begin;
        }

        @Override
        public void run() {
            String key = "test";
            long start = System.currentTimeMillis();
            int max = begin + 10000;
//            ShardedJedisPipeline pipelined = jedis.pipelined();
//            for (int i = begin; i < max; i++) {
//                String k = key + ":" + i;
//                for (int j = 0; j < 1000; j++)
//                    pipelined.hset(k, j + "ddd", j + "aaaaa");
//            }
//            pipelined.sync();
//
//            System.out.println("线程: " + Thread.currentThread().getName() + "完成，耗时：" + (System.currentTimeMillis() - start));

            ShardedJedisPipeline pipelinedExpire = jedis.pipelined();
            for (int i = begin; i < max; i++) {
                String k = key + ":" + i;
                pipelinedExpire.expire(k, 60);
            }
            pipelinedExpire.sync();
            System.out.println("设置完成...");
        }
    }

}
