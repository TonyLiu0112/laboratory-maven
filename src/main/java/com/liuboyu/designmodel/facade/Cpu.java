package com.liuboyu.designmodel.facade;

public class Cpu implements Boot {

	public void startup() {
		System.out.println("cpu startup");
	}

	public void shutdown() {
		System.out.println("cpu shutdown");
	}
	
}
