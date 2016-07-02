package com.liuboyu.designmodel.iterator;

/**
 * 迭代子模式
 * @author Bob
 * @Feb 26, 2015
 */
public class Main {
	
	public static void main(String[] args) {
		
		Collection collection = new MyCollection();
		Iterator iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}
