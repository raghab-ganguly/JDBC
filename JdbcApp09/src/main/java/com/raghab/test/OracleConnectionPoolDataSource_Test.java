package com.raghab.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class OracleConnectionPoolDataSource_Test {

	public static void main(String[] args) {
		Connection con1, con2 = null;
		try {
			MysqlConnectionPoolDataSource ds1 = new MysqlConnectionPoolDataSource();
			OracleConnectionPoolDataSource ds2 = new OracleConnectionPoolDataSource();
			ds1.setUrl("jdbc:mysql:///raghab");
			ds1.setUser("root");
			ds1.setPassword("root");
			con1 = ds1.getConnection();

			ds2.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ds2.setUser("system");
			ds2.setPassword("tiger");
			con2 = ds2.getConnection();

			System.out.println(con1.getClass().getName());
			System.out.println(con2.getClass().getName());
			if (con1 != null) {
				System.out.println("MySQL Connection Established");
			} else {
				System.out.println("MySQL Connection Failed");
			}
			if (con2 != null) {
				System.out.println("Oracle Connection Established");
			} else {
				System.out.println("Oracle Connection Failed");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
