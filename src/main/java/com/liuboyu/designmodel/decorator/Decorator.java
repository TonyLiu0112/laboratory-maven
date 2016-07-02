package com.liuboyu.designmodel.decorator;

/**
 * 装饰器（如何区分代理模式和装饰模式）
 * @author Bob
 * @Feb 24, 2015
 */
public class Decorator implements Sender {
	
	private Sender sender;
	
	public Decorator(Sender sender) {
		this.sender = sender;
	}

	public void sender() {
		System.out.println("Decorator - sender - 正在配置收件人信息...");
		sender.sender();
		System.out.println("Decorator - sender - 发送完毕！正在删除联系人...");
		System.out.println("完成");
	}

}
