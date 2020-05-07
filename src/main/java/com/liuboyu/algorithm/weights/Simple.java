package com.liuboyu.algorithm.weights;

import lombok.AllArgsConstructor;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.*;

/**
 * 权重算法实现
 * <p>
 * 思路：假设3台机器A、B、C，设置的权重比例为：A(20)、B(10)、C(70)，将比例映射为3个区间段：[0, 10)，[10, 30), [30, 100]
 * 然后在1-100间生成随机数，按照命中的区域来选择机器
 *
 * @author Tony
 */
public class Simple {

    public static void main(String[] args) {
        Simple s = new Simple();
        Server a = new Server("server:a", 20);
        Server b = new Server("server:b", 10);
        Server c = new Server("server:c", 70);
        s.init(a, b, c);


        Map<String, Integer> result = new TreeMap<String, Integer>() {{
            put("server:a", 0);
            put("server:b", 0);
            put("server:c", 0);
        }};
        for (int i = 0; i < 1000; i++) {
            Server choose = s.choose();
            result.put(choose.ip, result.get(choose.ip) + 1);
        }
        System.out.println(result);
    }

    private List<Tuple3<Integer, Integer, Server>> segments;
    private int max = 0;

    private void init(Server... servers) {
        segments = new ArrayList<>();
        TreeMap<Integer, Server> serverTreeMap = new TreeMap<>();
        for (Server server : servers) {
            serverTreeMap.put(server.weight, server);
        }
        Offset offset = new Offset(0);
        serverTreeMap.forEach((k, v) -> {
            int endOffset = k + offset.value;
            segments.add(Tuples.of(offset.value, endOffset, v));
            offset.value = endOffset;
        });
        max = offset.value;
    }

    private Server choose() {
        int hit = new Random().nextInt(max - 1);
        Optional<Server> server = segments.stream().filter(tuple -> tuple.getT1() <= hit && hit < tuple.getT2()).map(Tuple3::getT3).findFirst();
        if (server.isPresent()) {
            return server.get();
        }
        throw new NullPointerException();
    }

    @AllArgsConstructor
    private static class Offset {
        private int value;
    }

    @AllArgsConstructor
    private static class Server {
        private String ip;
        private int weight;
    }

}
