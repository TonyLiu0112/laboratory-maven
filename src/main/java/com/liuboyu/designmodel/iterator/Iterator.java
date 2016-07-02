package com.liuboyu.designmodel.iterator;

/**
 * 迭代接口
 * @author Bob
 * @Feb 26, 2015
 */
public interface Iterator {

	public Object previous();
	
	public Object next();
	
	public boolean hasNext();
	
	public Object first();
	
}