//得到数据库的连接
package com.wst.model;

import java.sql.*;

public class ConnDB {
	private Connection ct = null;

	public Connection getConn() {

		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2.得到连接
			ct = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/simpleLogin", "root", "root");

		} catch (Exception e) {
			// 一定要写上
			e.printStackTrace();
		}
		return ct;
	}
}
