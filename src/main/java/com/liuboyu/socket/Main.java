package com.liuboyu.socket;

import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.setReuseAddress(true);
            socket.setKeepAlive(true); // Will monitor the TCP connection is
            socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to
            socket.setSoLinger(true, 0); // Control calls close () method,

            socket.connect(new InetSocketAddress("115.29.168.108", 6379), 5000);

            InputStream inputStream = socket.getInputStream();
            System.out.println(inputStream.read());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new JedisConnectionException(ex);
        }
    }
}
