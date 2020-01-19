package com.liuboyu.redis.zip;

import lombok.SneakyThrows;
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
        new Thread(new Worker(pool.getResource())).start();
        System.out.println("正在写入...");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class Worker implements Runnable {

        private final ShardedJedis jedis;

        Worker(ShardedJedis jedis) {
            this.jedis = jedis;
        }

        @Override
        public void run() {
            ShardedJedisPipeline pipelined = jedis.pipelined();
            for (int i = 0; i < 100; i++) {
                String brandId = "brand_" + i;
                for (int j = 0; j < 100; j++) {
                    String shopId = "shop_" + j;
                    for (int k = 0; k < 1000; k++) {
                        String code = "code_" + k;
                        hsetTile(pipelined, brandId, shopId, code);
//                        hsetSharding(pipelined, brandId, shopId, code);
                    }
                }

            }
            pipelined.sync();
            System.out.println("写完了");
        }

        private void hsetTile(ShardedJedisPipeline pipelined, String brandId, String shopId, String code) {
            String key = "stock:" + brandId + ":" + shopId + ":" + code;
            try {
                pipelined.hset(MethodUtil.getBucketId(key), key, "64703022465076428889898");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void hsetSharding(ShardedJedisPipeline pipelined, String brandId, String shopId, String code) {
            String key = "stock:" + brandId + ":" + shopId + ":" + code;
            try {
                pipelined.hset(shardKey(key), code, "64703022465076428889898");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @SneakyThrows
        private String shardKey(String key) {
            long shardId = CRC64Util.hashByAlgo2((key).getBytes()) % 20000;
            return MethodUtil.getBucketId(key + ":" + shardId);
        }

    }

}
