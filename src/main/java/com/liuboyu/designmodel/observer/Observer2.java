package com.liuboyu.designmodel.observer;

/**
 * 观察者2 当观察的对象变化时被触发
 * @author Bob
 * @Feb 25, 2015
 */
public class Observer2 implements Observer {

	public void update() {
		System.out.println("observer2 is received");
	}

}
