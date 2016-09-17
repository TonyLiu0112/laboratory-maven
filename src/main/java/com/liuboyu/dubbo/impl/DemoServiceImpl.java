package com.liuboyu.dubbo.impl;

import com.liuboyu.dubbo.DemoService;

/**
 * Created by Tony on 9/17/16.
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String say(String name) {
        return "DemoService is invoked by " + name;
    }
}
