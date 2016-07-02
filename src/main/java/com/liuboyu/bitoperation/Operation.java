package com.liuboyu.bitoperation;

/**
 * java 位运算
 * @author liuboyu
 *
 */
public class Operation {
	
	public static void main(String[] args) {
		
		/* 	&
		 *  两个操作数中位都为1，结果才为1，否则结果为0
		 *  128 --> 10000000
		 *  129 --> 10000001
		 *  结果 --> 10000000
		 */
		int a = 128;
		int b = 129;
		System.out.println("a & b = " + (a&b));
		
		/* 	|
		 *  两个位只要有一个为1，那么结果就是1，否则就为0
		 *  128 --> 10000000
		 *  129 --> 10000001
		 *  结果 --> 10000001
		 */
		System.out.println("a | b = " + (a|b));
		
		/* 	~
		 *  如果位为0，结果是1，如果位为1，结果是0
		 *  简单的说就是将二进制下0变1 1变0
		 *  128 --> 10000000
		 *  结果 --> 01111111
		 */
		int _a = 2;
		System.out.println("~_a = " + (~_a));
		
		/* 	^
		 *  两个操作数的位中，相同则结果为0，不同则结果为1
		 *  15 --> 1111
		 *  2  --> 0010
		 *  结果--> 1101
		 */
		int __a = 15;
		int __b = 2;
		System.out.println("__a ^ __b = " + (__a ^ __b));
		
	}
	
}
