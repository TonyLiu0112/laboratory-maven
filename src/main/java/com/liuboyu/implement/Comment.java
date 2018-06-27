package com.liuboyu.implement;

import com.liuboyu.implement.res.RootRes;

import java.util.Optional;

public interface Comment {

    <T extends RootRes> Optional<T> find();

}
