package com.liuboyu.designmodel.observer;

/**
 * 观察者1 当观察的对象变化时被触发
 * @author Bob
 * @Feb 25, 2015
 */
public class Observer1 implements Observer {

	public void update() {
		System.out.println("observer1 is received");
	}

}
