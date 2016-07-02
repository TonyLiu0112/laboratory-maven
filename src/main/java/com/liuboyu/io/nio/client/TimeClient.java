package com.liuboyu.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeClient implements Runnable {

	private final int port = 8181;
	
	private final String host = "127.0.0.1";
	
	private Selector selector;
	
	private SocketChannel socketChannel;
	
	private AtomicBoolean stop = new AtomicBoolean(false);
	
	public TimeClient() throws Exception {
		selector = Selector.open();
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
	}
	
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (!stop.get()) {
			try {
				// 该方法一直阻塞到客户端有数据为止
				selector.select();
				// 获得客户端注册的事件迭代器 并迭代
				Iterator<SelectionKey> iterator = selector.keys().iterator();
				SelectionKey key = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					iterator.remove();
					try {
						handler(key);
					} catch (Exception e) {
						if (key != null) 
							key.cancel();
						if (key.channel() != null) 
							key.channel().close();
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	private void close() {
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doConnect() throws IOException {
		boolean connected = socketChannel.connect(new InetSocketAddress(host, port));
		if (connected)
			socketChannel.register(selector, SelectionKey.OP_READ);
		else 
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}

	private void handler(SelectionKey key) throws Exception {
		
		if (!key.isValid())
			return ;
		
		if (key.isConnectable()) {
			SocketChannel channel = SocketChannel.class.cast(key.channel());
			if (channel.finishConnect()) { 
				channel.register(selector, SelectionKey.OP_READ);
				doWrite(channel);
			}
		} else if (key.isReadable())
			read(key);
		else 
			System.out.println("avaliable opts not found");
	}

	private void doWrite(SocketChannel channel) throws IOException {
		byte[] req = "吃饭睡觉打豆豆...".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(req.length);
		buffer.put(req);
		buffer.flip();
		channel.write(buffer);
		if (buffer.hasRemaining()) 
			System.out.println("客户端请求成功!");
			
	}

	private void read(SelectionKey key) throws Exception {
		SocketChannel channel = SocketChannel.class.cast(key.channel());
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		int readBytes = channel.read(buffer);
		if (readBytes > 0) {
			byte[] data = buffer.array();
			System.out.println("来自服务器的消息:" + new String(data, "UTF-8"));
			
		}
	}

	
	public static void main(String[] args) throws Exception {
		
		new Thread(new TimeClient()).start();;
	}

}
