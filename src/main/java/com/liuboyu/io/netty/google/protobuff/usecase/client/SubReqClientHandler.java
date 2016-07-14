package com.liuboyu.io.netty.google.protobuff.usecase.client;

import com.liuboyu.io.netty.google.protobuff.entity.OrderProtos;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Tony on 7/14/16.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(createAddressbook(i));
        }
        ctx.flush();
    }

    private OrderProtos.AddressBook createAddressbook(int id) {
        OrderProtos.AddressBook.Builder addressBookBuilder = OrderProtos.AddressBook.newBuilder();
        OrderProtos.Person.Builder personBuilder = OrderProtos.Person.newBuilder();
        OrderProtos.Person.PhoneNumber.Builder phoneBuilder = OrderProtos.Person.PhoneNumber.newBuilder();

        phoneBuilder.setType(OrderProtos.Person.PhoneType.MOBILE)
                .setNumber("18721790735");

        personBuilder.setId(id)
                .setName("Tony" + id)
                .setEmail("liuboyu_work@icloud.com")
                .addPhone(phoneBuilder);

        return addressBookBuilder.addPerson(personBuilder).build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
