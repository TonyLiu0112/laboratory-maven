package com.liuboyu.designmodel.iterator;

/**
 * 集合接口
 * @author Bob
 * @Feb 26, 2015
 */
public interface Collection {
	
	/**
	 * 迭代器
	 * @return
	 */
	public Iterator iterator();
	
	/**
	 * 根据索引获取元素
	 * @param index
	 * @return
	 */
	public Object get(int index);
	
	/**
	 * 获取集合长度
	 * @return
	 */
	public int size();
	
}
