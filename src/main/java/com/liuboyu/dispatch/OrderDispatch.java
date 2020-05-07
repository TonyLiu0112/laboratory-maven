package com.liuboyu.dispatch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.liuboyu.dispatch.OrderDispatch.DataGenerator.Channel;
import static com.liuboyu.dispatch.OrderDispatch.DataGenerator.Channel.TB;
import static com.liuboyu.dispatch.OrderDispatch.DataGenerator.RedisOps.*;

/**
 * 订单分发
 *
 * @author Tony
 */
@Slf4j
public class OrderDispatch {

    public static final String redis_host = "localhost";
    public static final int redis_port = 6379;
    private static JedisPool jedisPool = new JedisPool(new JedisPoolConfig() {{
        setMaxIdle(40);
        setMaxTotal(40);
        setMaxWaitMillis(10000);
        setTestOnBorrow(false);
    }}, redis_host, redis_port);

    public static void main(String[] args) throws InterruptedException {
        DataGenerator.init();

        Jedis jedis = jedisPool.getResource();
        for (int i = 0; i < 20; i++) {
            jedis.del("dispatch:processed:TB180927" + i);
            new Thread(new SameCityDispatch(10000, "TB180927" + i)).start();
        }
        jedis.close();
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static class SameCityDispatch implements Runnable {

        private final int count;
        private final String orderNum;
        private final Jedis jedis = jedisPool.getResource();

        private SameCityDispatch(int count, String orderNum) {
            this.count = count;
            this.orderNum = orderNum;
        }

        @Override
        public void run() {
            String province = "江苏省", city = "苏州市";
            long begin = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
//                log.info("开始第" + (i + 1) + "次分配同城店铺..");
                String shopId = sameCity(jedis, city, TB, orderNum);
                if (i == count - 1) {
//                    log.info("未能找到可用的店铺, 分配结束.");
                    jedis.del("dispatch:processed:" + orderNum);
                    break;
                }
//                log.info("订单超时, 开始重新分配...................");
            }
            log.info("分配到店铺完成, 耗时: " + (System.currentTimeMillis() - begin) + "毫秒");
        }
    }

    private static String sameCity(Jedis jedis, String city, Channel channel, String orderNum) {
        Set<Tuple> shopStocks = getSameCityShopWithStock(jedis, city, channel);
        List<String> shopIds = filterUnprocessed(jedis, shopStocks, orderNum);
        if (CollectionUtils.isEmpty(shopIds))
            return null;
        // 上述店铺的周转
        TreeMap<Integer, String> turnovers = getSameCityShopWithTurnover(jedis, shopIds);
        String shopId = turnovers.firstEntry().getValue();
        markProcessedShop(jedis, orderNum, shopId);
        return shopId;
    }

    private static List<String> filterUnprocessed(Jedis jedis, Set<Tuple> shopStocks, String orderNum) {
        Map<String, String> processedShops = processedShops(jedis, orderNum);
        return shopStocks.stream().filter(tuple -> !processedShops.containsKey(tuple.getElement())).map(Tuple::getElement).limit(2).collect(Collectors.toList());
    }

    private static void sameProvince() {

    }

    private static void diffProvince() {

    }

    private static void sourceWarehouse() {

    }

    @Getter
    static class DataGenerator {



        public static void init() {
            AreaInfo.init();
            RedisShopData.init();
        }

        enum Channel {
            TB, JD;
        }

        static class AreaInfo {
            private final static Map<String, List<String>> provinceMap = new HashMap<>();
            private final static Map<String, String> city2Province = new HashMap<>();
            private final static File areaJsonFile = new File("/Users/Tony/Documents/workspaces/workspace/laboratory-maven/src/main/java/com/liuboyu/dispatch/area.json");

            public static void init() {
                try {
                    String json = FileUtils.readFileToString(areaJsonFile, "UTF-8");
                    JSONObject jsonObject = JSON.parseObject(json);
                    JSONArray w = jsonObject.getJSONArray("w");
                    for (int i = 0; i < w.size(); i++) {
                        JSONObject region = w.getJSONObject(i);
                        String province = region.getString("name");
                        if (!provinceMap.containsKey(province))
                            provinceMap.put(province, new ArrayList<>());
                        JSONArray citys = region.getJSONArray("city");
                        for (int j = 0; j < citys.size(); j++) {
                            JSONObject city = citys.getJSONObject(j);
                            String cName = city.getString("name");
                            if (StringUtils.equals(cName, "其他"))
                                continue;
                            provinceMap.get(province).add(cName);
                            if (!city2Province.containsKey(cName))
                                city2Province.put(cName, province);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        static class RedisShopData {

            public static void init() {
//                jedis.del("dispatch:processed:TB180927");
                String[] shopIds = {"19228", "17228", "19283", "12231", "14557", "17688", "19882", "15332", "10292", "11211"};
                Random random = new Random();
                Jedis resource = jedisPool.getResource();
                for (String shopId : shopIds) {
                    // 市 库存
                    resource.zadd("苏州市:TB", NumberUtils.toInt(shopId), shopId);
                    // 周转
                    resource.hset("sales:turnover", shopId, "" + (10 + random.nextInt(90)));
                }
                resource.close();
            }

        }

        static class RedisOps {

            /**
             * 库存redis设计
             * <li>数据结构: zscore</li>
             * <li>key: city:channel</li>
             * <li>score: 库存量</li>
             * <li>member: shopId</li>
             */
            public static Set<Tuple> getSameCityShopWithStock(Jedis jedis, String city, Channel channel) {
                String key = city + ":" + channel;
                return jedis.zrevrangeWithScores(key, 0, -1);
            }

            /**
             * 周转redis设计
             * <li>数据结构: hash</li>
             * <li>key: sales:turnover</li>
             * <li>field: shopId</li>
             * <li>value: turnover</li>
             */
            public static TreeMap<Integer, String> getSameCityShopWithTurnover(Jedis jedis, List<String> shopIds) {
                TreeMap<Integer, String> results = new TreeMap<>();
                String key = "sales:turnover";
                List<String> turnovers = jedis.hmget(key, shopIds.toArray(new String[0]));
                for (int i = 0; i < shopIds.size(); i++) {
                    results.put(NumberUtils.toInt(turnovers.get(i)), shopIds.get(i));
                }
                return results;
            }

            public static Map<String, String> processedShops(Jedis jedis, String orderNum) {
                return jedis.hgetAll("dispatch:processed:" + orderNum);
            }

            public static void markProcessedShop(Jedis jedis, String orderNum, String shopId) {
                jedis.hset("dispatch:processed:" + orderNum, shopId, "1");
            }
        }

    }
}
