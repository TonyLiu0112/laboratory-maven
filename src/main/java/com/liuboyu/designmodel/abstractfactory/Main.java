package com.liuboyu.designmodel.abstractfactory;

/**
 * 抽象工厂demo
 * @author Bob
 * @Feb 24, 2015
 */
public class Main {
	
	public static void main(String[] args) {
		Produce emailFactory = new EmailFactory();
		Sender emailSender = emailFactory.produce();
		
		Produce msgFactory = new MsgFactory();
		Sender msgSender = msgFactory.produce();
		
		System.out.println("start working......");
		emailSender.send();
		msgSender.send();
		
	}
}
