package com.liuboyu.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class KiisooTest {
    public static void main(String[] args) throws Exception {
        JedisCluster jedis = getDBJedisCluster();

        String str = jedis.hget("dc4ef4027bf", "shopid");
        long[] sids = null;

        String[] ss = StringUtils.split(str, ",");

        String ky = "flow.rt.day." + "43546";
        System.out.println("in:" + jedis.hget(ky, "in"));
        System.out.println("around:" + jedis.hget(ky, "around"));
        System.out.println("all:" + jedis.hget(ky, "all"));
        System.out.println("oc:" + jedis.hget(ky, "oc"));

        if (ss != null) {
            sids = new long[ss.length];
            for (int i = 0; i < ss.length; i++) {
                sids[i] = NumberUtils.toLong(ss[i]);
            }
            System.out.println(sids[0]);
        }

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
