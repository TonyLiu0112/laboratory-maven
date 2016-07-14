package com.liuboyu.io.netty.google.protobuff.usecase.server;

import com.liuboyu.io.netty.google.protobuff.entity.OrderProtos;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Tony on 7/14/16.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        OrderProtos.AddressBook addressBook = (OrderProtos.AddressBook) msg;
        addressBook.getPersonList().forEach(person -> {
            System.out.println("person Id: " + person.getId());
            System.out.println("person Name: " + person.getName());
            System.out.println("person email: " + person.getEmail());
            person.getPhoneList().forEach(phoneNumber -> {
                System.out.println("phone type is " + phoneNumber.getType() + ", phone number is " + phoneNumber.getNumber());
            });
            System.out.println();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
