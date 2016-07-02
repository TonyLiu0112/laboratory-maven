package com.liuboyu.designmodel.interfaceadapter;

/**
 * 抽象适配器
 * 场景：当需要用到一个有很多个方法的接口的其中某几个方法时，可以做一层适配
 * @author Bob
 * @Feb 24, 2015
 */
public abstract class AbstractAdapter implements Sender {

	public void emailSender() {};
	
	public void msgSender() {};
	
}
