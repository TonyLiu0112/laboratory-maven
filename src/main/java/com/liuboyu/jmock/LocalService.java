package com.liuboyu.jmock;

/**
 * Created by Tony on 02/07/2017.
 */
public class LocalService {

    private RemoteService remoteService;

    public LocalService(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    public String doSomething1() {
        return remoteService.queryInfo("1");
    }

    public String doSomething2() {
        return remoteService.queryInfo("2");
    }

    public String doSomething3() {
        return remoteService.queryInfo("");
    }

}
