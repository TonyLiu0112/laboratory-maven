package com.liuboyu.aop;

public class MyWorker implements Worker {

	public String dowork(String name) {
		String str = name + " 在实验工作。。。";
		System.out.println(str);
		return str;
	}

}
