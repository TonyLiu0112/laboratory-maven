package com.liuboyu.designmodel.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractSubject implements Subject {

	private List<Observer> observerList = new LinkedList<Observer>();
	
	public void add(Observer observer) {
		observerList.add(observer);
	}

	public void delete(Observer observer) {
		observerList.remove(observer);
	}

	public void notifyAllObserver() {
		for (Observer observer : observerList) {
			observer.update();
		}
	}

}
