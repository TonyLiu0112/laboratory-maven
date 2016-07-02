package com.liuboyu;

import java.util.ArrayList;

/**
 * Created by Tony on 3/5/16.
 */
public class DoTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("dfjj12");
        list.add("dfc");
        list.add("47878");
        list.add("fijjj48885");
        deleteNum(list);
    }

    private static void deleteNum(ArrayList<String> list) {
//        for (int x = 0; x < list.size(); x++) {
//            String s = list.get(x);
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) > '0' && s.charAt(i) < '9') {
//                    list.remove(s);
//                    x--;
//                    break;
//                }
//            }
//        }
//
//        for (String s : list) {
//            System.out.println(s.toString());
//        }
        String flag = "false";
        boolean a = Boolean.valueOf(flag);
        System.out.println(a);
    }

}
