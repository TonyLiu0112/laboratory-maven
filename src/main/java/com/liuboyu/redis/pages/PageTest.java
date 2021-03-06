package com.liuboyu.redis.pages;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;

public class PageTest {

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
        ShardedJedis jedis = pool.getResource();
        for (int i = 1; i <= 100; i++) {
            jedis.set("tt_" + i, i + "");
        }
    }

}
