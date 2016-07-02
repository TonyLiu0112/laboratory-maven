package com.liuboyu.thread.lock;

import java.util.concurrent.CountDownLatch;

public class Snyc {
	private final static A a = new A();
	private final static CountDownLatch latch = new CountDownLatch(4);
	public static void main(String[] args) {
		for (int i = 0; i < 4; i ++) {
			new Thread(new Runnable() {
				
				@SuppressWarnings("static-access")
				public void run() {
					try {
						latch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Myentry entry = new Myentry();
					for (int i = 0; i < 5; i ++) {
//						String name = "刘博宇";
						try {
							Thread.currentThread().sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						a.callName(entry);
					}
				}
			}, "线程"+i).start();
			latch.countDown();
		}
	}
	
}

class A {
	
	@SuppressWarnings("static-access")
	public void callName(Myentry entry) {
		
		synchronized (entry) {
			try {
				System.out.println("线程["+Thread.currentThread().getName()+"]正在处理..");
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

class Myentry {}