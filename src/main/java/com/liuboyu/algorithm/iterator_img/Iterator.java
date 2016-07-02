package com.liuboyu.algorithm.iterator_img;

/**
 * 图的遍历(就是遍历一个二维数组)
 * 遍历如下的树
 * 			   1
 *           * *
 *         *   *
 * 		 2	   3 ** 5
 * 		 *
 * 		 *
 * 		 4
 * 此树需要用而为数组表示，其中：i表示顶点，j表示是否有边，存在边则j＝1，否则j＝∞
 * 
 * 1.深度优先遍历（遍历树打最大深度）
 * 2.广度优先遍历（遍历树的最大广度）
 * 
 * @author liuboyu
 *
 */
public class Iterator {
	
	private static String[][] IMG = new String[10][10];
	
	public static void main(String[] args) {
		IMG.toString();
	}
	
}
