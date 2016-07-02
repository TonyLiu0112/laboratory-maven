package com.liuboyu.byte_bit;

/**
 * bit 是最小单位 即二进制里面的 0 1
 * 1 byte = 8bit
 * 
 * @author liuboyu
 *
 */
public class Test {

	public static void main(String[] args) {
		// 数字 十六进制 二进制 十进制 互转
		System.out.println(Integer.toHexString(20));
		System.out.println(Integer.parseInt("14", 16));
		System.out.println(Integer.toBinaryString(0));
		System.out.println(Integer.toBinaryString(1));
		
		// 字符串转二进制
		String str = "刘博宇";
		byte[] strByte = str.getBytes();
		String binary = "";
		for (byte sb : strByte) {
			binary += Integer.toBinaryString(sb) + " ";
		}
		System.out.println(binary);
		
		// 二进制转字符串
		String[] binaryItem = binary.split(" ");
		char[] binaryChar = new char[binaryItem.length];
		for (int i = 0; i < binaryChar.length; i++) {
			
			// 将二进制字符串转化成数字数组
			char[] tempChar = binaryItem[i].toCharArray();
			int[] tempInt = new int[tempChar.length];
			for (int j = 0; j < tempInt.length; j++) {
				tempInt[j] = tempChar[j] - 48;
			}
			
			int sum = 0;
			for (int j = 0; j < tempInt.length; j++) {
				System.out.println(tempInt[tempInt.length-1-j]);
				sum += tempInt[tempInt.length-1-j] << 1;
			}
			binaryChar[i] = (char)sum;
			System.out.println();
		}
		System.out.println("aaa >> " + String.valueOf(binaryChar));
		
	}
	
}
