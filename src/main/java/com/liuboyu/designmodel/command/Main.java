package com.liuboyu.designmodel.command;

/**
 * 命令模式:简单实现;
 * 
 * @author Bob
 * @Feb 26, 2015
 */
public class Main {
	
	public static void main(String[] args) {
		
		Receiver receiver = new Receiver();
		Command command = new MyCommand(receiver);
		
		Invoker invoker = new Invoker(command);
		
		invoker.action();
		
	}
}
