package com.liuboyu.cglib;

public class Domain {
	public static void main(String[] args) {
		
		CglibProxy cglib = new CglibProxy();
		
		HelloWorld hw = (HelloWorld) cglib.newProxy(new HelloWorld());
		System.out.println(hw.sayHello("刘博宇", 3));
	}
}
