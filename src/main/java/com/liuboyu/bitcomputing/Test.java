package com.liuboyu.bitcomputing;

/**
 * 位运算
 *
 * @author liuboyu
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " -> " + Integer.toBinaryString(i));
        }
        System.out.println(30 << 3);
    }

    public void old() {
        // 1、左移( << )
        // 0000 0000 0000 0000 0000 0000 0000 0101 然后左移2位后，低位补0：//
        // 0000 0000 0000 0000 0000 0000 0001 0100 换算成10进制为20
        System.out.println("5 << 2 = " + (5 << 2));// 运行结果是20

        // 2、右移( >> ) 高位补符号位
        // 0000 0000 0000 0000 0000 0000 0000 0101 然后右移2位，高位补0：
        // 0000 0000 0000 0000 0000 0000 0000 0001
        System.out.println("5 >> 2 = " + (5 >> 2));// 运行结果是1

        // 3、无符号右移( >>> ) 高位补0
        // 例如 -5换算成二进制(取到是补码,正数绝对值到二进制,取反+1)后为：0101 取反加1为1011
        // 1111 1111 1111 1111 1111 1111 1111 1011
        // 我们分别对5进行右移3位、 -5进行右移3位和无符号右移3位：
        System.out.println(5 >> 3);// 结果是0
        System.out.println(-5 >> 3);// 结果是-1
        System.out.println(-5 >>> 3);// 结果是536870911

        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(-5 >> 3));
        System.out.println(Integer.toBinaryString(-5 >>> 3));
    }


}
