package com.liuboyu.cglib;

public class HelloWorld {
	
	public String sayHello(String name, long age) {
		
		System.out.println("我被调用了呢!!!!!!! " + name + " " + age);
		return name + (age*10);
		
	}
	
}
