package com.liuboyu.designmodel.headfirst.proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 服务端的接口，用户描述服务
 * <p>
 * Created by Tony on 30/10/2016.
 */
public interface IService extends Remote {
    String say(String name) throws RemoteException;
}
