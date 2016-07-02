package com.liuboyu.designmodel.memento;

/**
 * 备忘录模式
 * @author Bob
 * @Feb 27, 2015
 */
public class Main {
	
	public static void main(String[] args) {
		Original org = new Original("Bob");
		Memento memento = org.createMemento();
		
		System.out.println("原始值：" + org.getValue());
		org.setValue("Liu");
		System.out.println("新的值：" + org.getValue());
		org.restoreMemento(memento);
		System.out.println("恢复后：" + org.getValue());
	}
	
}
