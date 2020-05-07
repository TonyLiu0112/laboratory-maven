package com.liuboyu.thread.sync;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        MockService mockService = new MockService();
        new Thread(mockService::aaa).start();
        mockService.bbb();
        System.out.println("finished");
        Thread.sleep(10000);
    }


}
