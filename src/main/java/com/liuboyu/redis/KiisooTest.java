package com.liuboyu.redis;

import com.github.wxisme.bloomfilter.bitset.RedisBitSet;
import com.github.wxisme.bloomfilter.common.BloomFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class KiisooTest {

    private static final double FALSE_POSITIVE_PROBABILITY = 0.01;
    private static final int EXPECTED_NUMBER_OF_ELEMENTS = 100000000;

    public static void main(String[] args) throws Exception {
        JedisCluster jedis = getDBJedisCluster();

        BloomFilter<String> blacklistFilter = new BloomFilter<>(FALSE_POSITIVE_PROBABILITY, EXPECTED_NUMBER_OF_ELEMENTS);
        blacklistFilter.bind(new RedisBitSet(jedis, "flow.bm.blacklist"));

        String key = "44701.2059aa01a6cc";
        System.out.println(blacklistFilter.contains(key));

    }

    private static JedisCluster getDBJedisCluster() throws Exception {
        String hostAndPorts = "10.173.32.198:6380,10.173.32.68:6380,10.173.32.198:6381,10.173.32.68:6381,10.173.32.69:6380,10.173.32.89:6380,10.173.32.69:6381,10.173.32.89:6381";
        String[] hps = StringUtils.split(hostAndPorts, ',');
        String[] hap;
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        for (String hp : hps) {
            hap = StringUtils.split(hp, ':');
            jedisClusterNodes.add(new HostAndPort(hap[0], NumberUtils.toInt(hap[1])));
        }

        return new JedisCluster(jedisClusterNodes);
    }
}
