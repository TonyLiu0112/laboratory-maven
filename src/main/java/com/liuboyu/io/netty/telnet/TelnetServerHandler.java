package com.liuboyu.io.netty.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

/**
 * Created by Tony on 7/15/16.
 */
public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("welcome to " + InetAddress.getLocalHost().getHostName() + "!\\r\\n");
        ctx.write("It is " + new Date() + " now.");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        String response = "";
        boolean close = false;
        if (request.isEmpty()) {
            response = "Please type something! \r\n";
        } else if ("bye".equals(request)) {
            response = "Have a good day! \r\n";
            close = true;
        } else {
            response = "Did you say " + request + "?\r\n";
        }

        ChannelFuture channelFuture = ctx.writeAndFlush(response);
        if (close) channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
