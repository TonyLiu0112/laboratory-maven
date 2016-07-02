package com.liuboyu.designmodel.abstractfactory;

public class MsgFactory implements Produce {

	public Sender produce() {
		return new MsgSender();
	}

}
