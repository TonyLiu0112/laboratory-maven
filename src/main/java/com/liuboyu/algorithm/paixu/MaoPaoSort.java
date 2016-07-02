package com.liuboyu.algorithm.paixu;

/**
 * 冒泡排序
 * 缺点：效率极其低下，如果数组过长，则成指数增长
 * @author liuboyu
 *
 */
public class MaoPaoSort {
	
	public static void main(String[] args) {
		
		int[] item = {2, 8 , 6, 4, 6, 1, 9};

		
		for (int i = 0; i < item.length; i++) {
			
			for (int j = 0; j < item.length-1; j++) {
				if (item[j] < item[j+1]) {
					int t = item[j];
					item[j] = item[j + 1];
					item[j + 1] = t;
							
				}
				
			}
			
		}
		
		
		System.out.println();
		for (int x = 0; x < item.length; x++ ) {
			System.out.println(item[x]);
		}
		
	}
	
}
