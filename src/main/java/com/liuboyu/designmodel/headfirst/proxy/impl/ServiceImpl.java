package com.liuboyu.designmodel.headfirst.proxy.impl;

import com.liuboyu.designmodel.headfirst.proxy.IService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务的实现类
 * 当前服务实现类继承了UnicastRemoteObject类，利用了Java的服务发现机制
 * <p>
 * Created by Tony on 30/10/2016.
 */
public class ServiceImpl extends UnicastRemoteObject implements IService {

    protected ServiceImpl() throws RemoteException {
    }

    @Override
    public String say(String name) {
        return "Server says, 'hello " + name + "'";
    }

    public static void main(String[] args) {
        try {
            IService myServer = new ServiceImpl();
            Naming.rebind("remoteHello", myServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
