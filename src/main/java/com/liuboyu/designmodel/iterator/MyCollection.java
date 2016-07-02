package com.liuboyu.designmodel.iterator;

public class MyCollection implements Collection {
	
	String[] item = {"A", "B", "C", "D", "E", "F"};

	public Iterator iterator() {
		return new MyIterator(this);
	}

	public Object get(int index) {
		return item[index];
	}

	public int size() {
		return item.length;
	}

}
