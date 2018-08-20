package com.liuboyu.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {
	
	/**
	 * 非切片客户端
	 */
	private Jedis jedis;
	
	/**
	 * 非切片连接池
	 */
	private JedisPool jedisPool;
	
	/**
	 * 切片模式客户端
	 */
	@SuppressWarnings("unused")
	private ShardedJedis shardedJedis;
	
	/**
	 * 切片模式连接池
	 */
	private ShardedJedisPool shardedJedisPool;
	
	private JedisPoolConfig poolconfig;
	
	private void initConfig() {
		
		if (poolconfig != null) 
			return ;
		
		poolconfig = new JedisPoolConfig();
		poolconfig.setMaxIdle(5);
		poolconfig.setMaxWaitMillis(10000);
		poolconfig.setTestOnBorrow(false);
	}
	
	public RedisClient init() {
		initConfig();
		
		jedisPool = new JedisPool(poolconfig, "www.tony666.com", 6379);
		
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo("www.tony666.com", 6379, "master"));
		shardedJedisPool = new ShardedJedisPool(poolconfig, shards);
		
		jedis = jedisPool.getResource();
		shardedJedis = shardedJedisPool.getResource();
		return this;
	}
	
	/**
	 * 通过key操作redis string 类型数据
	 * @param key
	 */
	public void operationString(String key) {
		
//		System.out.println("do delete data by key --> " + jedis.del("name"));
//		System.out.println(jedis.setnx("name", "Bob"));
//		System.out.println(jedis.set("name", "my name is Bob.Liu"));
//		System.out.println(jedis.setex("name", 2, "Bob"));
//		try {
//			Thread.sleep(3000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(jedis.get("name"));
//		System.out.println(jedis.type("name"));
//		System.out.println("cover data for key of name... " + jedis.set("name", "My name is cover,I'm Bob"));
		
//		System.out.println("清空库中所有数据：" + jedis.flushDB());
		
	}
	
	/**
	 * 通过key操作redis list 类型数据
	 */
	public void operationList() {
//		System.out.println("clean redis db：" + jedis.flushDB());
//		
//		for (int i = 20; i >= 10; i --) 
//			jedis.lpush("name", "Bob"+i);
//			
//		
//		for (int i = 0; i < 10; i ++) 
//			jedis.lpush("name", "Bob" + i);
//		
//		System.out.println("specified index ---> " + jedis.lindex("name", 17));
		
//		System.out.println("befor sort --> " + jedis.lrange("name", 0, 100));
//		SortingParams sparams = new SortingParams();
//		sparams.alpha();
//		sparams.asc();
//		List<String> list = jedis.sort("name", sparams);
//		for (String str : list) 
//			System.out.println(str + " hash:"+str.hashCode());
//		System.out.println("atfer sort --> " + jedis.sort("name", sparams));
//		System.out.println("deleting element " + jedis.ltrim("name", 2, -1));
//		System.out.println(jedis.lrange("name", 0, 10));
	
	}
	
	public void operationHash() {
		System.out.println("clean redis db：" + jedis.flushDB());
		System.out.println(jedis.hset("person", "刘亦菲", "男"));
		System.out.println(jedis.hset("person", "芙蓉", "男"));
		System.out.println(jedis.hset("person", "凤姐", "男"));
		System.out.println("keys - " + jedis.hkeys("person"));
		System.out.println("values - " + jedis.hvals("person"));
		System.out.println("get all ? - " + jedis.hgetAll("person"));
	}
	
	public static void main(String[] args) {
		RedisClient redisClient = new RedisClient().init();
		redisClient.operationHash();
	}
	
}
