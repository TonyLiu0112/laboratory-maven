package com.liuboyu.bloomfilter;

import java.util.BitSet;

/**
 * 布隆过滤器
 * @author liuboyu
 *
 */
public class BloomFilter {

	static int DEFAULT_SIZE = Integer.MAX_VALUE;
	static BitSet bitSet = new BitSet(DEFAULT_SIZE);
	// 质数能明显降低错误率
	static int[] hashKey = {3, 5, 7, 11, 13, 31, 37, 61};
	static SimpleHash[] simpleHashs = new SimpleHash[hashKey.length];

	static {
		// 初始化key的hash列阵
		for (int i = 0; i < hashKey.length; i ++) {
			simpleHashs[i] = new SimpleHash(DEFAULT_SIZE, hashKey[i]);
		}
	}

	public static void add(String value) {
		if (value == null)
			return ;

		for (SimpleHash simpleHash : simpleHashs)
			bitSet.set(simpleHash.hash(value), true);
	}

	public static boolean contains(String value) {
		if (value == null)
			return false;

		boolean result = true;
		for (SimpleHash simpleHash : simpleHashs) {
			result = result && bitSet.get(simpleHash.hash(value));
			if (!result)
				break;
		}

		return result;

	}

	public static void main(String[] args) {

		for (int i = 0; i < 100000; i ++) {
			BloomFilter.add(i+"");
		}

		long startTime = System.nanoTime();
		System.out.println(BloomFilter.contains("99999"));
		System.out.println((System.nanoTime() - startTime));

	}

}

class SimpleHash {

	private int cap;
    private int seed;

	public  SimpleHash(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }

	/**
	 * hash算法
	 * 字符串哈希，选取好的哈希函数很重要
	 * @param value
	 * @return
	 */
	public int hash(String value) {
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++) {
            result = seed * result + value.charAt(i);
        }
        return (cap - 1) & result;
    }

}
