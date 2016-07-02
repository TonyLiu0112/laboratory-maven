package com.liuboyu.hdfsoutput;

import java.util.Random;

/**
 * RSSI算法帮助类
 * @author liuboyu
 *
 */
public class RssiHelper {

	public enum Range {
		INSTORE, // 入店
		TRAFFIC  // 未入店
	}
	
	/**
	 * 根据店铺配置信息获得生成数据的rssi范围
	 * @param confRssiMax
	 * @param confRssiMiddle
	 * @param confRssiMin
	 * @param range
	 * @return
	 */
	public static int[] getRssi(int confRssiMax, int confRssiMiddle, int confRssiMin, Range range) {
		if (range == Range.INSTORE) 
			return instoreRandom(confRssiMax, confRssiMiddle, confRssiMin);
		else 
			return trafficRandom(confRssiMax, confRssiMiddle, confRssiMin);
	}

	private static int[] instoreRandom(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		if ((new Random().nextInt(2)+1) == 1)
			return instoreAlgorithmA1(confRssiMax, confRssiMiddle, confRssiMin);
		else 
			return instoreAlgorithmA2(confRssiMax, confRssiMiddle, confRssiMin);
	}
	
	private static int[] trafficRandom(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		if ((new Random().nextInt(2)+1) == 1)
			return trafficAlgorithmA1(confRssiMax, confRssiMiddle, confRssiMin);
		else 
			return trafficAlgorithmA2(confRssiMax, confRssiMiddle, confRssiMin);
	}
	
	/**
	 * 店外算法1
	 * 	入参最大场强 < 配置最大场强 && 入参最大场强 >= 配置中间场强 && 入参最小场强 <  配置中间场强
	 * @param confRssiMax
	 * @param confRssiMiddle
	 * @param confRssiMin
	 * @return
	 */
	private static int[] trafficAlgorithmA1(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		Random random = new Random();
		// 最大rssi
		int randomMax = Math.abs(confRssiMiddle) - Math.abs(confRssiMax);
		int rssiMax = 0 - (random.nextInt(randomMax - 1) + 1 + Math.abs(confRssiMax));
		// 最小rssi
		int i = 0;
		int rssiMin = 0 - (((i = random.nextInt(20))==0?++i:i) + Math.abs(confRssiMiddle));
		int[] array = {rssiMax, rssiMin};
		return array;
	}
	
	/**
	 * 店外算法2
	 * 	入参最大场强 < 配置中间场强 && 入参最大场强 >= 配置最小场强
	 * @param confRssiMax
	 * @param confRssiMiddle
	 * @param confRssiMin
	 * @return
	 */
	public static int[] trafficAlgorithmA2(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		Random random = new Random();
		int rssiMax = 0 - (random.nextInt((Math.abs(confRssiMin) - Math.abs(confRssiMiddle))) + Math.abs(confRssiMiddle));
		int i = 0;
		int rssiMin = 0 - (((i = random.nextInt(20))==0?++i:i) + Math.abs(rssiMax));
		int[] array = {rssiMax, rssiMin};
		return array;
	}
	
	/**
	 * 入店算法1 
	 * 入参最大场强 >= 配置最大场强
	 * @param confRssiMax
	 * @param confRssiMiddle
	 * @param confRssiMin
	 * @return
	 */
	private static int[] instoreAlgorithmA1(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		Random random = new Random();
		// 最大场强
		int rssiMax = 0 - (random.nextInt(Math.abs(confRssiMax) - 1) + 1);
		// 最小场强
		int randomOffset = Math.abs(confRssiMin) - Math.abs(rssiMax);
		int rssiMin = 0 - (random.nextInt(Math.abs(randomOffset) - 1) + 1 + Math.abs(rssiMax));
		int[] array = {rssiMax, rssiMin};
		return array;
	}
	
	/**
	 * 入店算法2 
	 * 入参最大场强 < 配置最大场强 && 入参最大场强 >= 配置中间场强 && 入参最小场强 >= 配置中间场强
	 * @param confRssiMax
	 * @param confRssiMiddle
	 * @param confRssiMin
	 * @return
	 */
	private static int[] instoreAlgorithmA2(int confRssiMax, int confRssiMiddle, int confRssiMin) {
		Random random = new Random();
		// 最大rssi
		int randomMax = Math.abs(confRssiMiddle) - Math.abs(confRssiMax);
		int rssiMax = 0 - (random.nextInt(randomMax - 1) + 1 + Math.abs(confRssiMax));
		// 最小rssi 需处理临界点问题
		int randomMin = Math.abs(confRssiMiddle) - Math.abs(rssiMax);
		int rssiMin = 0;
		if (randomMin == 1) 
			rssiMin = 0 - (Math.abs(rssiMax) + 1);
		else if (randomMin == 0)
			rssiMin = rssiMax;
		else
			rssiMin = 0 - (random.nextInt(randomMin - 1) + 1 + Math.abs(rssiMax));
		int[] array = {rssiMax, rssiMin};
		return array;
	}
	
	public static void main(String[] args) {
		int confRssiMax = -45, confRssiMiddle = -65, confRssiMin = -80;
		int[] arry1 = trafficAlgorithmA2(confRssiMax, confRssiMiddle, confRssiMin);
		System.out.println(arry1[0] + " " + arry1[1]);
	}
}
