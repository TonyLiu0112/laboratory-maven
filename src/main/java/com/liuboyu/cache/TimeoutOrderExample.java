package com.liuboyu.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 订单超时example.
 *
 * @author Tony
 */
public class TimeoutOrderExample {

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderService();
        TimeoutService timeoutService = new TimeoutService();
        orderService.setTimeoutService(timeoutService);
        timeoutService.setOrderService(orderService);

        for (int i = 0; i < 10; i++) {
            orderService.create(new OrderReq(i + "", "NUM_" + i, 1), true);
        }
        System.out.println("=========== wait for timeout ===========");
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Setter
    @Slf4j
    private static class OrderService {

        private TimeoutService timeoutService;

        private void create(OrderReq req, boolean enabled) {
            // do something.
            log.info("第{}次处理订单:{}.", req.getCount(), req.getNum());
            if (enabled)
                timeoutService.register(req, new Random().nextInt(50) + 10);
        }

    }

    @Setter
    @Slf4j
    private static class TimeoutService {

        private OrderService orderService = new OrderService();

        ExpiringMap<String, OrderReq> expiringMap = ExpiringMap.builder()
                .maxSize(5000)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .expirationListener((key, value) -> {
                    log.info("订单{}超时", key);
                    OrderReq order = (OrderReq) value;
                    order.setCount(order.getCount() + 1);
                    orderService.create(order, false);
                })
                .variableExpiration()
                .build();

        private void register(OrderReq order, int expSec) {
            expiringMap.put(order.getNum(), order, expSec, TimeUnit.SECONDS);
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class OrderReq {
        private String id;
        private String num;
        private int count;
    }

}
