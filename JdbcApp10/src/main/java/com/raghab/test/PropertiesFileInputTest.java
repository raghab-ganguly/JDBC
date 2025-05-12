package com.raghab.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class PropertiesFileInputTest {
	public static void main(String[] args) {
		Connection con = null;
		OracleConnectionPoolDataSource ds = null;
		Properties ps = null;
		FileInputStream fis = null;
		String url = null;
		String user = null;
		String password = null;
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			ps = new Properties();

			ps.load(fis);
			url = ps.getProperty("url");
			user = ps.getProperty("user");
			password = ps.getProperty("password");

			ds = new OracleConnectionPoolDataSource();
			ds.setURL(url);
			ds.setUser(user);
			ds.setPassword(password);
			con = ds.getConnection();
			if (con != null) {
				System.out.println("Connection Established");
			} else {
				System.out.println("Connection Failed!!!");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
