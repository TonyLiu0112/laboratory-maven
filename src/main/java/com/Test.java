package com;

public class Test {

    public static void main(String[] args) {
        String s1 = new String("2");
        s1.intern();
        String s2 = "2";
        System.out.println(s1 == s2);


        String s3 = new String("3") + new String("3");
        s3.intern();
        String s4 = "33";
        System.out.println(s3 == s4);
    }

}
