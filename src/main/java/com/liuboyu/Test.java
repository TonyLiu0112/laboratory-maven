package com.liuboyu;

import java.util.*;

public class Test {

    public static void main(String[] args) {

        List<Map<String, Object>> list = new ArrayList<>();

        Set<Long> hashSet = new HashSet<>();
        hashSet.add(1233L);

        Map<String, Object> map = new HashMap<>();
        map.put("needName", hashSet);

        list.add(map);

        Set<String> set = (Set<String>) list.get(0).get("needName");

        String str = "1233";
        long s = 1233L;
        System.out.println(set.contains(str)); // false
        System.out.println(set.contains(s));   // true


    }

}
