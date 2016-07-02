package com.liuboyu.designmodel.bridge;

/**
 * mysql数据源简单实现
 * @author Bob
 * @Feb 24, 2015
 */
public class MysqlDS implements DataSource {

	public void connection() {
		System.out.println("get mysql datasource");
	}
	
}
