package com.liuboyu.io.netty.timeserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 通道处理适配器
 *
 * @author liuboyu
 * @date 2015年5月12日
 */
public class TimeServerHandler extends SimpleChannelInboundHandler {

    private int counter;

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, Object)
     * 读取通道数据
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 为解决粘包 拆包问题
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
//		System.out.println("The time server receive order : " + body + " ; the counter is " + ++counter);

        // 已解决拆包粘包问题
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + " ; the counter is " + ++counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
