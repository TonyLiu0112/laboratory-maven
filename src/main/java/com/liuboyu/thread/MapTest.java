package com.liuboyu.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    private static final Object lock = new Object();
    private static final Map<Integer, List<String>> map = new ConcurrentHashMap<>();

    public static class Register implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    int key = 10;
                    synchronized (lock) {
                        if (map.containsKey(key)) {
                            map.get(key).add(key + "");
                        } else {
                            map.put(key, new ArrayList<String>() {{
                                add(key + "");
                            }});
                        }
                    }
                    Thread.sleep(500L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Reader implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    int key = 10;
                    synchronized (lock) {
                        map.getOrDefault(key, Collections.emptyList()).forEach(v -> {
                            try {
                                Thread.sleep(2000L);
                                System.out.println(v);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HashMap map = new HashMap();
        new Thread(new Register()).start();
        new Thread(new Reader()).start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
