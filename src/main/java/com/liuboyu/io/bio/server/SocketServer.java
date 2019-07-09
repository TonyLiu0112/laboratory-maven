package com.liuboyu.io.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * BIO模型- 服务端
 *
 * @author bobo
 */
public class SocketServer extends Thread {


    // 接受和发送数据缓冲区
    private ByteBuffer send = ByteBuffer.allocate(1024);
    private ByteBuffer receive = ByteBuffer.allocate(1024);

    public int port = 5173;

    ServerSocketChannel ssc = null;

    Selector selector = null;

    @SuppressWarnings("unused")
    private void init() {
        ServerSocketChannel serverChannel = null;
        try {
            // 建立通道
            serverChannel = ServerSocketChannel.open();
            // 设置通道为非阻塞模式
            serverChannel.configureBlocking(false);
            //获得通道serversocket 并绑定端口
            ServerSocket serverSocket = serverChannel.socket();
            serverSocket.bind(new InetSocketAddress(port));
            // 选择器
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务启动完毕！");
            send.put("data from server:".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverChannel != null)
                try {
                    serverChannel.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }

    @SuppressWarnings("unused")
    private void listener() {
        for (; ; ) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    // 这里记得手动的把他remove掉，不然selector中的selectedKeys集合不会自动去除
                    iterator.remove();
                    dealKey(selectionKey);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unused")
    private void dealKey(SelectionKey selectionKey) throws Exception {
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText;
        String sendText;
        int count = 0;

        // 测试此键的通道是否已准备好接受新的套接字连接。  
        if (selectionKey.isAcceptable()) {
            // 返回为之创建此键的通道。  
            server = (ServerSocketChannel) selectionKey.channel();

            // 此方法返回的套接字通道（如果有）将处于阻塞模式。  
            client = server.accept();
            // 配置为非阻塞  
            client.configureBlocking(false);
            // 注册到selector，等待连接  
            client.register(selector, SelectionKey.OP_READ
                    | SelectionKey.OP_WRITE);
        } else if (selectionKey.isReadable()) {
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();
            // 将缓冲区清空以备下次读取
            receive.clear();
            // 读取服务器发送来的数据到缓冲区中
            client.read(receive);

            System.out.println(new String(receive.array()));

            selectionKey.interestOps(SelectionKey.OP_WRITE);
        } else if (selectionKey.isWritable()) {
            // 将缓冲区清空以备下次写入
            send.flip();
            // 返回为之创建此键的通道。
            client = (SocketChannel) selectionKey.channel();

            // 输出到通道
            client.write(send);

            selectionKey.interestOps(SelectionKey.OP_READ);
        }
    }
}
