package com.liuboyu.redis.zip;

import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

public class RedisZipTest {

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
        System.out.println("正在写入...");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void pipeline() {

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
            ShardedJedisPipeline pipelined = jedis.pipelined();
            for (int i = begin; i < 10000000; i++) {
                try {
//                    pipelined.hset("stock:$brandId:$erpShop:ddddddd" + i, MethodUtil.getBucketId("stock:$brandId:$erpShop.ddddddd" + i), "64703022465076428889898");
                    pipelined.hset("stock:$brandId:$erpShop:" + (CRC64Util.hashByAlgo2(("ddddddd" + i).getBytes()) / 500), "ddddddd" + i, "64703022465076428889898");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            pipelined.sync();
            System.out.println("写完了");
        }
    }

}
