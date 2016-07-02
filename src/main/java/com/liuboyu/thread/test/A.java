package com.liuboyu.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class A implements Runnable {

	private static AtomicBoolean flag = new AtomicBoolean(false);

	@Override
	public void run() {
		
		while(!flag.get()) {
			
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new A(), "liuboyu-t1");
		t.start();
		Thread.sleep(1000);
		A.flag.set(true);
		System.out.println(A.flag.get());

	}
	
	
	
}

