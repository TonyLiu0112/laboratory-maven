package com.liuboyu.designmodel.visitor;

public class MyVisitor implements Visitor {

	public void visit(Node node) {
		node.operation();
	}

}
