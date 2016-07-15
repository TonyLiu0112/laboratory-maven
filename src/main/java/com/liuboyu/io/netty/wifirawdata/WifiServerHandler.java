package com.liuboyu.io.netty.wifirawdata;

import com.kiisoo.aegis.common.rawdata.RawDataRecord;
import com.kiisoo.aegis.common.rawdata.protocol.alinket.report.WifiRawShortDataParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class WifiServerHandler extends SimpleChannelInboundHandler {

    int counter = 0;
    WifiRawShortDataParser wifiRawShortDataParser = new WifiRawShortDataParser();

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        byte[] bytes = new byte[bb.readableBytes()];
        bb.getBytes(0, bytes);
        RawDataRecord record = wifiRawShortDataParser.parse(bytes);
        counter++;
        if (record != null)
            System.out.println(record.getSmac() + " " + counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
