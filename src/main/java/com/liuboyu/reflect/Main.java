package com.liuboyu.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Query1", "liuboyu");
		map.put("Query2", "liuboyu2");
		map.put("Query3", "liuboyu3");
		map.put("Query4", "liuboyu4");
		map.put("Query5", "liuboyu5");
		map.put("Query6", "liuboyu6");
		Model model = new Model();
		Class<Model> clazz = (Class<Model>) model.getClass();
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods ) {
			String key = method.getName().split("set").length > 1?method.getName().split("set")[1]:"";
			if (map.containsKey(key)) 
				method.invoke(model, map.get(key));
		}
		
		for (Method method : methods ) {
			if (method.getName().startsWith("get")) 
				System.out.println(method.invoke(model));
		}
	}
}
