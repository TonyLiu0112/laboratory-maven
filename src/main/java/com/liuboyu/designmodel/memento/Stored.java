package com.liuboyu.designmodel.memento;

/**
 * 此类范例实现存储memento
 * 个人感觉无必要
 * @author Bob
 * @Feb 27, 2015
 */
public class Stored {
	
	private Memento memento;

	public Stored(Memento memento) {
		this.memento = memento;
	}

	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
}
