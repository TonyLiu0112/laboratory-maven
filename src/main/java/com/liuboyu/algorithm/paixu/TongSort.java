package com.liuboyu.algorithm.paixu;

/**
 * 桶排序
 * 缺点 需要申请桶数组 非常浪费内存
 * @author liuboyu
 *
 */
public class TongSort {
	
	public static void main(String[] args) {
		
		// source
		int[] item = {2,5,3,5,8};
		// 桶
		int[] result = new int[9];
		
		// 遍历item 将数值作为index放入到桶中  多次出现 +1
		for (int i = 0; i < item.length; i ++) 
			result[item[i]] = result[item[i]] + 1;
		
		System.out.println();
		
		int index = 0;
		for (int i = result.length-1 ; i >= 0; i --) {
			
			if (result[i] == 0) 
				continue ;
			
			for (int j = 0; j < result[i]; j ++) {
				item[index++] = i;
			}
		}
		
		for (int i = 0; i < item.length; i++) {
			System.out.println(item[i]);
		}
	}
	
}
