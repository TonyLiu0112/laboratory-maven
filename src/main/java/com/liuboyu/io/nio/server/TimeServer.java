package com.liuboyu.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeServer {
	
	private final int port = 8181;
	
	private ServerSocketChannel servChannel;
	
	private Selector selector;
	
	private AtomicBoolean stop = new AtomicBoolean(false);
	
	public TimeServer() {
		try {
			// 初始化服务通道
			servChannel = ServerSocketChannel.open();
			// 设置端口
			InetSocketAddress address = new InetSocketAddress(port);
			// 绑定通道端口
			servChannel.socket().bind(address);
			servChannel.configureBlocking(false);
			// 开启多路复用器
			selector = Selector.open();
			// 注册通道到多路复用器
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * 服务端通过轮询的方式监听客户端请求
	 * @throws Exception 
	 */
	public void listener() throws Exception {
		System.out.println("success to start server");
		while (!stop.get()) {
			// 该方法一直阻塞到客户端有数据为止
			selector.select();
			// 获得客户端注册的事件迭代器 并迭代
			Iterator<SelectionKey> iterator = selector.keys().iterator();
			SelectionKey key = iterator.next();
			while (iterator.hasNext()) {
				handlerInput(key);
				iterator.remove();
			}
				
		}
	}

	private void handlerInput(SelectionKey key) throws Exception {
		
		if (!key.isValid()) 
			return ;
			
		
		// 
		if (key.isAcceptable()) {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			channel.configureBlocking(false);
//			channel.write(ByteBuffer.wrap(new String("管道畅通...准备读取数据...").getBytes()));
			channel.register(selector, SelectionKey.OP_READ);
		} 
		if (key.isReadable()) {
			read(key);
		}
	}

	@SuppressWarnings("static-access")
	private void read(SelectionKey key) throws IOException, InterruptedException {
		SocketChannel channel = SocketChannel.class.cast(key.channel());
		// 创建一个读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		// 将客户端输入的数据读取到缓冲区中
		channel.read(buffer);
		byte[] data = new byte[buffer.remaining()];
		buffer.get(data);
		System.out.println("来自客户端：" + new String(data));
		// 休眠10秒 模拟服务端计算逻辑
		
		Thread.currentThread().sleep(10000);
		
		// 回写到客户端
		byte[] response = "数据已经处理完毕,哈哈...".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(response.length);
		writeBuffer.put(response);
		writeBuffer.flip();
		channel.write(writeBuffer);
		
	}
	
	public static void main(String[] args) throws Exception {
		TimeServer server = new TimeServer();
		server.listener();
	}
	
}
