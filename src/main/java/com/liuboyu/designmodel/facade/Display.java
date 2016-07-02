package com.liuboyu.designmodel.facade;

public class Display implements Boot {

	public void startup() {
		System.out.println("display start");
	}

	public void shutdown() {
		System.out.println("display shutdown");
	}

}
