package com.liuboyu.designmodel.bridge;

/**
 * 简单的实现桥
 * @author Bob
 * @Feb 24, 2015
 */
public class JDBCBridge extends Bridge {

	public JDBCBridge(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public void getConnection() {
		getDataSource().connection();
	}
	
}
