package com.liuboyu.designmodel.bridge;

/**
 * oracle 数据源简单实现
 * @author Bob
 * @Feb 24, 2015
 */
public class OracleDS implements DataSource {

	public void connection() {
		System.out.println("get oracle datasource");
	}

}
