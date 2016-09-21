package com.liuboyu.generic;

import java.util.List;

/**
 *
 * Created by Tony on 2/14/16.
 */
public class Demo {

    public static void main(String[] args) {
        MyGinterfaces<Box> mg = new MyGinterfaces<>(new Box());

    }


    /**
     * 这里的list就是一个原生态类型,尽量不要使用;
     * 正确用法应该是使用 List<Object> 来明确泛型类型.
     * @param list
     * @param obj
     */
    static void unsafeadd(List list, Object obj) {
        list.add(obj); // 这里编译器给出了警告
    }

}
