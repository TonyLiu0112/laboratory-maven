package com.liuboyu.designmodel.observer;

public interface Subject {
	
	public void add(Observer observer);
	
	public void delete(Observer observer);
	
	public void notifyAllObserver();
	
	public void operation();
	
}
