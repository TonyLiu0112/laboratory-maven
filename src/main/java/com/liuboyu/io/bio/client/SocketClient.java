package com.liuboyu.io.bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {
	
	private int port = 5173;
	
	private String host = "127.0.0.1";
	
	private void send(byte[] msg) {
		OutputStream out = null;
		InputStream in = null;
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			out = socket.getOutputStream();
			
			out.write(msg);
			out.flush();
			
			System.out.println("消息已经发送，正在等待服务端响应...");
			// 等待服务端响应，否则一直阻塞
			in = socket.getInputStream();
			int reciveBuffer = 0;
			byte[] reciveBuffers = new byte[128];
			if ((reciveBuffer=in.read(reciveBuffers)) != -1) {
				String resultMsg = "from server:" + (new String(reciveBuffers, 0, reciveBuffer));
				System.out.println(resultMsg);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out!=null) 
					out.close();
				if (in != null) 
					in.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void doInput() throws IOException {
		while (true) {
			System.out.println("请输入:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String msg = reader.readLine();
			
			if (msg.equals("exit")) 
				break;
			
			send(msg.getBytes());
		}
		System.out.println("bye bye !");
	}
	
	public static void main(String[] args) {
		SocketClient client = new SocketClient();
		try {
			client.doInput();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
