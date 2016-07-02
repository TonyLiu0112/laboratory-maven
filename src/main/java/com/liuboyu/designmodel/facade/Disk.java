package com.liuboyu.designmodel.facade;

public class Disk implements Boot {

	public void startup() {
		System.out.println("disk start");
	}

	public void shutdown() {
		System.out.println("disk shutdown");
	}

}
