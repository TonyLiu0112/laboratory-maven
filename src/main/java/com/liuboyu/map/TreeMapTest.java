package com.liuboyu.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeMapTest {

	public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("liuboyu", "帅哥");
        System.out.println(map.remove("liuboyu1"));
        System.out.println(map.size());

        Map<String, String> treeMap = new TreeMap<String, String>(){{
			put("validate", "");
			put("sendcode", "");
			put("action", "");
			put("register", "");
		}};
		
		for (Entry<String, String> entry : treeMap.entrySet()) {
			System.out.println(entry.getKey());
		}
		
	}
	
}
