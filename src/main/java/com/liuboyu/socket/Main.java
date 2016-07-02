package com.liuboyu.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import redis.clients.jedis.exceptions.JedisConnectionException;

public class Main {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            // ->@wjw_add
            socket.setReuseAddress(true);
            socket.setKeepAlive(true); // Will monitor the TCP connection is
            // valid
            socket.setTcpNoDelay(true); // Socket buffer Whetherclosed, to
            // ensure timely delivery of data
            socket.setSoLinger(true, 0); // Control calls close () method,
            // the underlying socket is closed
            // immediately
            // <-@wjw_add

            socket.connect(new InetSocketAddress("115.29.168.108", 6379), 5000);
        } catch (IOException ex) {
            throw new JedisConnectionException(ex);
        }
    }
}
