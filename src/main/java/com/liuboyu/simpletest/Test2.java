package com.liuboyu.simpletest;

public class Test2 implements IA, IB {

	@Override
	public void say() {
		System.out.println("I am ");
	}
	
	public static void main(String[] args) {
		IA a = new Test2();
		a.say();
		
		IB b = new Test2();
		b.say();
	}

}

interface IA {
	void say();
}

interface IB {
	void say();
}