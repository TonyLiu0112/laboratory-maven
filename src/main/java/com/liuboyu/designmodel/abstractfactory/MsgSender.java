package com.liuboyu.designmodel.abstractfactory;

public class MsgSender implements Sender{

	public void send() {
		System.out.println("发送短信");
	}

}
