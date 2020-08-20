package org.com.liurz.iresources.activiti.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	// 连接mysql数据库
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/activiti";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";

	// 查询存储过程数据
	public void test() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "{CALL test()}"; // 调用存储过程
		CallableStatement cs = connection.prepareCall(sql);
		boolean hadResults = cs.execute();
		int i = 0;
		while (hadResults) {
			ResultSet rs = cs.getResultSet();
			while (rs != null && rs.next()) {
				String name = rs.getString(1);
				System.out.println("id:" + rs.getString(1) + ",create_time:" + rs.getString(2) + ",name:"
						+ rs.getString(3) + ",task_id:" + rs.getString(4));
			}
			hadResults = cs.getMoreResults(); // 检查是否存在更多结果集
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JDBCUtil util = new JDBCUtil();
		util.test();

	}

}
