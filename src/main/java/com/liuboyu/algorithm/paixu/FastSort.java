package com.liuboyu.algorithm.paixu;


/**
 * 快速排序
 * @author liuboyu
 *
 */
public class FastSort {
	// {9, 1, 2, 4, 6, 2, 3, 7, 2, 5, 6, 1, 3, 6, 8, 22, 6, 1};
	static int[] item = {6, 1, 2, 4, 6, 2, 3, 7, 2, 5, 6, 1, 3, 6, 8, 1};
	
	public static void main(String[] args) {
		System.out.println("排序前:");
		for (int i = 0; i < item.length; i++) {
			System.out.print(item[i] + " ");
		}
		new FastSort().fastSort(0, item.length-1);
		System.out.println("\n排序后:");
		for (int i = 0; i < item.length; i++) {
			System.out.print(item[i] + " ");
		}
		
		
	}
	
	public void fastSort(int left, int right) {
		
		if (left >= right) {
			return ;
		}
		
		boolean ok = false;
		int refer = item[left];
		int i = right;
		int j = left;
		// 从右往左
		for (; i >= 0; i--) {
			
			int rightVal = item[i];
			
			if (i == j) {
				item[i] = item[left];
				item[left] = rightVal;
				ok = true;
				break;
			}
			
			if (rightVal < refer) {
				// 从左往右
				for (; j < right+1; j ++) {
					
					int leftVal = item[j];
					
					if (i == j) {
						item[j] = item[left];
						item[left] = leftVal;
						ok = true;
						break;
					}
					
					if (leftVal > refer) {
						item[i] = item[j];
						item[j] = rightVal;
						break;
					}
				}
			}
			if (ok) 
				break;
			
		}
		
		fastSort(left, j-1);
		fastSort(j+1, right);
		
	}
	
}
