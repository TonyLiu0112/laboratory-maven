package com.liuboyu.designmodel.facade;

public class Computer implements Boot {
	
	private Cpu cpu;
	private Disk disk;
	private Display display;
	
	public Computer() {
		cpu = new Cpu();
		disk = new Disk();
		display = new Display();
	}

	public void startup() {
		cpu.startup();
		disk.startup();
		display.startup();
	}

	public void shutdown() {
		cpu.shutdown();
		disk.shutdown();
		display.shutdown();
	}

}
