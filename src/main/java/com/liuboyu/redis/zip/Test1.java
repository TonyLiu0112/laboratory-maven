package com.liuboyu.redis.zip;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;

public class Test1 {

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

    public static void main(String[] args) {
        ShardedJedisPipeline pipelined = pool.getResource().pipelined();
        for (int i = 0; i < 10000000; i++) {
            try {
                int shardId = i / 500;
                pipelined.hset("emp.stock.shop." + shardId, "" + i, "10457");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pipelined.sync();
        System.out.println("写完了");
    }

}
