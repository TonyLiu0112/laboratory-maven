package com.liuboyu.designmodel.observer;

public class MySubject extends AbstractSubject {

	public void operation() {
		notifyAllObserver();
	}

}
