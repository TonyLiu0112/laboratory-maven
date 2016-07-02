package com.liuboyu.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrackLockMain {
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		final Operations op = new Operations();
		
		// 调用线程1,获得锁后，停止1分钟
		Thread r1 = new Thread(new Runnable() {
			
			public void run() {
				op.exec(10000L);
			}
		});
		Thread r2 = new Thread(new Runnable() {
			
			public void run() {
				op.exec(20000L);
			}
		});
		
		
		r1.start();
		r2.start();
		
	}
	
}
class Operations {
	
	// 创建一个显示锁非公平策略显示锁
	final ReentrantLock rwLock = new ReentrantLock();
	
	@SuppressWarnings("static-access")
	public void exec(long waitTime) {
		// 获得锁
		rwLock.lock();
		try {
			System.out.println(String.format("Operation - doRead() - I am [%s] read now ", Thread.currentThread().getName()));
			Thread.currentThread().sleep(waitTime);
			System.out.println(String.format("Operation - doRead() - I am [%s] read OK.%s", Thread.currentThread().getName(), waitTime));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			rwLock.unlock();
		}
	}
	
	
}
