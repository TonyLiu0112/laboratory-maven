package com.liuboyu.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tony on 10/04/2017.
 */
public class SortExample {

    public static void main(String[] args) {
        List<Double> list = new ArrayList<Double>() {{
            add(5d);
            add(3d);
//            add(99d);
//            add(1d);
//            add(32d);
//            add(11d);
//            add(6d);
        }};

        int endOffset = 2;
        list.subList(0, endOffset <= list.size() ? endOffset : list.size());

        // sort 升序排序
        Collections.sort(list, (o1, o2) -> {
            System.out.println(list);
            System.out.println(o1 + " " + o2); // 这里o1 = 3   o2 = 5
            return o1.compareTo(o2);
//            return -1;
        });

        // print
        list.forEach(System.out::println);

    }

}
