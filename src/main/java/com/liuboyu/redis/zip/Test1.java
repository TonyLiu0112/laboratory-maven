package com.liuboyu.redis.zip;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
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

    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            MethodUtil.getBucketId(CRC64Util.hashByAlgo2(("ddddddd" + i).getBytes()) + "");
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - begin));
    }

}
