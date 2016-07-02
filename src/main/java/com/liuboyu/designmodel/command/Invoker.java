package com.liuboyu.designmodel.command;

/**
 * 命令发送者
 * @author Bob
 * @Feb 26, 2015
 */
public class Invoker {
	
	private Command command;
	
	public Invoker(Command command) {
		this.command = command;
	}
	
	public void action() {
		command.exec();
	}
	
}
