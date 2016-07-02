package com.liuboyu.designmodel.abstractfactory;

public class EmailSender implements Sender {

	public void send() {
		System.out.println("发送邮件");
	}

}
