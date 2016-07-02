package com.liuboyu.designmodel.command;

public class MyCommand implements Command {
	
	private Receiver recevier;
	
	public MyCommand(Receiver recevier) {
		this.recevier = recevier;
	}

	public void exec() {
		recevier.action();
	}

}
