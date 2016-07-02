package com.liuboyu.designmodel.visitor;

public abstract class AbstractNode implements Node {
	
	public abstract void accept(Visitor visitor);
	
}
