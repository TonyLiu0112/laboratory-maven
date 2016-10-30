package com.liuboyu.designmodel.headfirst.proxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 代理客户端
 * <p>
 * Created by Tony on 30/10/2016.
 */
public class Client {
    public static void main(String[] args) {
        try {
            IService service = (IService) Naming.lookup("rmi://127.0.0.1/remoteHello");
            String message = service.say("Tony");
            System.out.println(message);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
