package com.liuboyu.auto.unboxed;

import java.util.Comparator;

/**
 * 基本类型总是优先于装箱基本类型
 * <p>
 * Created by Tony on 3/28/16.
 */
public class Comparators {

    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                return first < second ? -1 : (first == second ? 0 : 1);
            }
        };
        // 这里输出为1,返回错误的结果,因为这里比较的时候比较的是对象,并没有触发java的自动拆包
        System.out.println(comparator.compare(new Integer(42), new Integer(42)));
    }

}
