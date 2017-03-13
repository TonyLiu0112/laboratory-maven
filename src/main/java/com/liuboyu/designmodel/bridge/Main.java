package com.liuboyu.designmodel.bridge;

/**
 * 桥接模式入口
 * 抽象一个持有数据源接口的桥
 * 将不同数据库对数据源接口的实现设置到桥上
 * @author Bob
 * @Feb 24, 2015
 */
public class Main {
	/**
	 * 模拟客户端
	 * @param args
	 */
	public static void main(String[] args) {
		DataSource oracleDs = new OracleDS();
		DataSource mysqlDs = new MysqlDS();
		
		Bridge bridge = new JDBCBridge(mysqlDs);
		bridge.getConnection();
		
		bridge.setDataSource(oracleDs);
		bridge.getConnection();
	}
}
