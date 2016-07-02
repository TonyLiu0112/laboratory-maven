package com.liuboyu.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tony on 6/20/16.
 */
public class MapRemove {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put("list", new ArrayList<String>(){{
            add("1");
            add("2");
            add("3");
        }});

        List<String> list = (List<String>) map.remove("list");

        System.gc();
        System.out.println(list.size());
        System.out.println(map.containsKey("list"));
        System.out.println(map.containsValue(list));

    }

}
