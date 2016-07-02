package com.liuboyu.jdbc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * mysql driver
 * @author liuboyu
 *
 */
public class MysqlDriver {
	
	private static final String URL = "jdbc:mysql://192.168.0.193:3306/p2p";
	private static final String USER = "p2p";
	private static final String PASSWORD = "p2p";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	

	public static void main(String[] args) throws Exception {
		Connection conn = getConnection();
		System.out.println(conn);
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
}
