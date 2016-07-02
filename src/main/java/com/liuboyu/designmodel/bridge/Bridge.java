package com.liuboyu.designmodel.bridge;

/**
 * 抽象的桥
 * @author Bob
 * @Feb 24, 2015
 */
public abstract class Bridge {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public abstract void getConnection();
}
