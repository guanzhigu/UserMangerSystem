//�õ����ݿ������
package com.wst.model;

import java.sql.*;

public class ConnDB {
	private Connection ct = null;

	public Connection getConn() {

		try {
			// 1.��������
			Class.forName("com.mysql.jdbc.Driver");
			// 2.�õ�����
			ct = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/simpleLogin", "root", "root");

		} catch (Exception e) {
			// һ��Ҫд��
			e.printStackTrace();
		}
		return ct;
	}
}
