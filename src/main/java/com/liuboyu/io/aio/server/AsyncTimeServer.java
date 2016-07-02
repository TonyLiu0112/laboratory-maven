package com.liuboyu.io.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;


public class AsyncTimeServer implements Runnable {
	
	private int port;
	
	CountDownLatch latch;

	private AsynchronousServerSocketChannel asynchronousServerSocketChannel;
	
	public AsyncTimeServer(int port) {
		this.port = port;
		try {
			asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
			asynchronousServerSocketChannel.bind(new InetSocketAddress(this.port));
			System.out.println("the server is start, port is >> " + this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		latch = new CountDownLatch(1);
		
		doAccept();
		try {
			latch.await();
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	private void doAccept() {
		asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
	}
	
}
