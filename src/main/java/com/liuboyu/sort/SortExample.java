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
            add(99d);
            add(1d);
            add(32d);
            add(11d);
            add(6d);
        }};

//        int endOffset = 2;
//        list.subList(0, endOffset <= list.size() ? endOffset : list.size());

        // sort 升序排序
        list.sort((o1, o2) -> {
            if (o1 > o2)
                return -1;
            else if (o1 < o2)
                return 1;
            return 0;
        });

        // print
        System.out.println(list);

    }

}
