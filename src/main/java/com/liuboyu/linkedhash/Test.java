package com.liuboyu.linkedhash;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings({ "rawtypes", "serial", "unchecked"})
public class Test extends LinkedHashMap {

	private int cacheSize = 10;

	public Test(int cacheSize) {
		super((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, false);
		this.cacheSize = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry eldest) {
		return size() > cacheSize;
	}

	public static void main(String[] args) {
		Map<String, String> map = new Test(5);
		map.put("1", "1");
		map.put("2", "1");
		map.put("3", "1");
		map.put("4", "1");
		map.put("5", "1");
		map.get("1");
		map.put("6", "1");
		map.get("2");
		map.put("7", "1");
		map.get("3");
		map.put("8", "1");
		map.get("4");
		map.put("9", "1");
		map.get("5");
		map.put("10", "1");
		
		for (java.util.Map.Entry<String, String> set : map.entrySet()) {
			System.out.println(set.getKey());
		}
	}
}
