package com.liuboyu.io.netty.delimiter.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author liuboyu
 * @date 2015年5月13日
 */
public class EchoServer {

	public void bind(int port) {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap boot = new ServerBootstrap();
		try {
			boot.group(bossGroup, workGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// 分隔符
							ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new EchoServerHandler());
						}
					});

			ChannelFuture f = boot.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer().bind(8080);
	}

}
