package com.liuboyu.designmodel.observer;

/**
 * 观察者模式入口
 * @author Bob
 * @Feb 25, 2015
 */
public class Main {
	
	public static void main(String[] args) {
		MySubject mysubject = new MySubject();
		Observer observer1 = new Observer1();
		Observer observer2 = new Observer2();
		
		mysubject.add(observer1);
		mysubject.add(observer2);
		
		mysubject.notifyAllObserver();
		System.out.println("------------------------------");
		mysubject.delete(observer1);
		mysubject.notifyAllObserver();
	}
	
}
