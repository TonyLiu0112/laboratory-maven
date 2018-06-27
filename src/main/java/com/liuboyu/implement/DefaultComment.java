package com.liuboyu.implement;

import com.liuboyu.implement.res.RootRes;

import java.util.Optional;

public class DefaultComment implements Comment {

    @Override
    public <T extends RootRes> Optional<T> find() {
        RootRes res = new RootRes();
        res.setContent("你好, 世界!");
        return Optional.of((T) res);
    }
}
