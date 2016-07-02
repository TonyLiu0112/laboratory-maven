package com.liuboyu.aop;

public class Log implements Operation {

	public void before(String info) {
		System.out.println("i am the before log " + info);
	}

	public void after(String info) {
		System.out.println("i ma the after log " + info);
	}
	
}
