package com.liuboyu.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Tony on 6/16/16.
 */
public class IntAddressTest {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("mf.kiisoo.com");
        System.out.println(inetAddress.getCanonicalHostName());

//        byte[] address = {115, 29, (byte) 188, 9};
//        InetAddress ia = InetAddress.getByAddress("mf.kiisoo.com", address);
//        System.out.println(ia);

//        InetAddress inetAddress = InetAddress.getLocalHost();
//        System.out.println(inetAddress);

    }

}
