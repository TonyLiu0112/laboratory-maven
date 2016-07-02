package com.liuboyu.designmodel.facade;

/**
 * 外观模式入口
 * @author Bob
 * @Feb 24, 2015
 */
public class Main {
	public static void main(String[] args) {
		Computer pc = new Computer();
		pc.startup();
		System.out.println("正在工作...");
		pc.shutdown();
	}
}
