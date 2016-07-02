package com.liuboyu.designmodel.abstractfactory;

public class EmailFactory implements Produce {

	public Sender produce() {
		return new EmailSender();
	}

}
