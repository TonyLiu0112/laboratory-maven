package com.liuboyu.jdk8.class1;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;

/**
 * Java8 -> Lambda表达式
 * <p>
 * Created by Tony on 3/29/16.
 */
public class Demo1 {

    public static ThreadLocal<DateFormatter> dateFormatterThreadLocal = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("yyyy-MM-dd")));

    private void test() {
        Runnable runnable = () -> System.out.println("1121");

        JButton button = new JButton();
        button.addActionListener(e -> System.out.println(e.getActionCommand()));
    }
}

interface IntPred {
    boolean test(Integer value);
}

class Test1 {
    boolean check(Predicate<Integer> predicate) {
        return false;
    }

//    boolean check(IntPred predicate) {
//        return false;
//    }

    public static void main(String[] args) {
        Test1 t = new Test1();
        t.check(x -> x > 5);
    }

}
