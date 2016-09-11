package com.liuboyu.thread.test;

public class A implements Runnable {

	private static volatile boolean flag = true;
    private static int aaa = 1;

	@Override
	public void run() {

        while(flag) {
        }
		aaa = 2;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new A(), "liuboyu-t1");
		t.start();
		Thread.sleep(1000);
		flag = false;
		for(;;) {
            System.out.println(aaa);
            Thread.currentThread().sleep(1000);
        }
	}
	
	
	
}

