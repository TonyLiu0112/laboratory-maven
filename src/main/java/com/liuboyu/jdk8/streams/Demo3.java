package com.liuboyu.jdk8.streams;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 纯函数. 下面的Lambda表达式有无副作用, 或者说他们是否改变了程序状态?
 * <p>
 * Created by Tony on 3/31/16.
 */
public class Demo3 {

    /**
     * 部分伪代码
     * 问: foreach中的Lambda表达式是否有副作用?
     * 答: 有副作用, 因为他改变了外界对象的状态值.
     */
    private void method() {
//        AtomicInteger count = new AtomicInteger(0);
//        List<String> origins = album.musicians().forEach(musician -> count.incrementAndGet();)
    }

}
