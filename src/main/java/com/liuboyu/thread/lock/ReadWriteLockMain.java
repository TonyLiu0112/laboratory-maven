package com.liuboyu.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain {
	
	public static void main(String[] args) {
		final Operation op = new Operation();
		
		// 读线程1 休眠10s
		Thread r1 = new Thread(new Runnable() {
			
			public void run() {
				op.doRead(10000L);
			}
		});
		// 读线程2 休眠20s
		Thread r2 = new Thread(new Runnable() {
			
			public void run() {
				op.doRead(20000L);
			}
		});
		// 读线程3 休眠30s
		Thread r3 = new Thread(new Runnable() {
			
			public void run() {
				op.doRead(30000L);
			}
		});
		// 写操作线程
		Thread w1 = new Thread(new Runnable() {
			
			public void run() {
				op.doWrite();
			}
		});
		
		r1.start();
		r2.start();
		r3.start();
		
		w1.start();
		
	}
	
}
/**
 * 操作类
 * @author Bob
 * @Mar 17, 2015
 */
class Operation {
	// 定义一个读写锁
	final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	// 定义一个读锁
	final Lock r = rwLock.readLock();
	// 定义一个写锁
	final Lock w = rwLock.writeLock();
	
	@SuppressWarnings("static-access")
	public void doRead(long waitTime) {
		// 获得读锁
		r.lock();
		try {
			System.out.println(String.format("Operation - doRead() - I am [%s] read now ", Thread.currentThread().getName()));
			// 持有读锁的状态下休眠
			Thread.currentThread().sleep(waitTime);
			System.out.println(String.format("Operation - doRead() - I am [%s] read OK.%s", Thread.currentThread().getName(), waitTime));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			r.unlock();
		}
	}
	
	public void doWrite() {
		// 获得一个写锁
		w.lock();
		try {
			System.out.println(String.format("Operation - doRead() - I am [%s] wirte now ", Thread.currentThread().getName()));
		} finally {
			// 释放一个写锁
			w.unlock();
		}
	}
	
}
