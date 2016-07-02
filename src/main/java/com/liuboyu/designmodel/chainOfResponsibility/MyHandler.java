package com.liuboyu.designmodel.chainOfResponsibility;

public class MyHandler extends AbstractHandler implements Handler {

	private String name;
	
	public MyHandler(String name) {
		this.name = name;
	}

	public void operation() {
		System.out.println(name + " operation.");
		
		if (getHandler() != null) 
			getHandler().operation();
		
	}

}
