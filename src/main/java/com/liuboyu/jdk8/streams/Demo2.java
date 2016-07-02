package com.liuboyu.jdk8.streams;

import com.google.common.base.Predicate;

import java.util.stream.Stream;

/**
 * 1. 判断下列两个方法是惰性求值还是及早求值;
 * 2. 下面的Stream函数是高阶函数吗?为什么?
 * <p>
 * Created by Tony on 3/31/16.
 */
public interface Demo2<T> {

    /**
     * 1. 及早求值
     * 2. 是高阶函数,因为参数也是一个函数
     *
     * @param predicate
     * @return
     */
    boolean anyMatch(Predicate<? extends T> predicate);

    /**
     * 1. 惰性求值
     * 2. 不是高阶函数,因为参数不是一个函数
     *
     * @param maxSize
     * @return
     */
    Stream<T> limit(long maxSize);

}
