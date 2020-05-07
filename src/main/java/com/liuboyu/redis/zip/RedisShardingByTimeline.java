package com.liuboyu.redis.zip;

import lombok.SneakyThrows;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RedisShardingByTimeline {

    private static ShardedJedisPool pool;

    private static Map<String, Long> bucketSegment = new HashMap<>();

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost", 6379);
        List<JedisShardInfo> list = new LinkedList<>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
        bucketSegment.put("offset", System.currentTimeMillis() / 1000 / 60);
        bucketSegment.put("pre-bucket", 10000L);
        bucketSegment.put("next-bucket", 20000L);
    }

    public static void main(String[] args) throws Exception {
        ShardedJedis jedis = pool.getResource();
        ShardedJedisPipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 100; i++) {
            String brandId = "brand_" + i;
            for (int j = 0; j < 100; j++) {
                String shopId = "shop_" + j;
                for (int k = 0; k < 1000; k++) {
                    String code = "code_" + k;
                    hsetSharding(pipelined, brandId, shopId, code);
                }
            }

        }
        pipelined.sync();
    }

    private static void hsetSharding(ShardedJedisPipeline pipelined, String brandId, String shopId, String code) {
        String key = "stock:" + brandId + ":" + shopId + ":" + code;
        try {
            pipelined.hset(shardKey(key), code, "64703022465076428889898");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static String shardKey(String key) {
        long shardId = CRC64Util.hashByAlgo2((key).getBytes()) % 20000;
        return null; // MethodUtil.getBucketId(key + ":" + shardId);
    }

}
