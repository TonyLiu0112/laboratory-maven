package com.liuboyu.methods;

/**
 * Created by Tony on 3/6/16.
 */
public class Test {
    public static void main(String[] args) {

        sort(null, 1, 1);
    }

    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset < a.length;
        assert length >= 0 && length <= a.length - offset;
        System.out.println("说好的异常呢");
    }

}
