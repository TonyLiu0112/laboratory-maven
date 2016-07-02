package com.liuboyu.designmodel.bridge;

/**
 * 简单的实现桥
 * @author Bob
 * @Feb 24, 2015
 */
public class JDBCBridge extends Bridge {

	public void getConnection() {
		getDataSource().connection();
	}
	
}
