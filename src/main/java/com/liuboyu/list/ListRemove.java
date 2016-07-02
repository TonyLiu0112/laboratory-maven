package com.liuboyu.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tony on 3/2/16.
 */
public class ListRemove {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>() {{
            add(new Integer(1));
            add(new Integer(2));
            add(new Integer(3));
            add(new Integer(4));
        }};

//        int size = list.size();
//        for (int i = 0; i < size; i ++) {
//            list.remove(0);
//        }

        System.out.println(list.size());

        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(list.size());

    }

}
