package com.liuboyu;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {

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

        encode();

    }

    public static void encode() throws IOException {

        String theString2 = IOUtils.toString(new FileInputStream(new
                File("/Users/Tony/Documents/工作doc/申万宏源/在线投资顾问/二期/code/bwaccessadvisor-二期/bwaccessadvisor-web/src/main/resources/swagger.yml")), "UTF-8");

        String encode = URLEncoder.encode(theString2, "UTF-8");
        System.out.println(encode);

    }

}
