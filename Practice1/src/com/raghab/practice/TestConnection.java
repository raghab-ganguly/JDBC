package com.raghab.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) {
		oracle.jdbc.driver.OracleDriver driver = null;
		Connection con = null;
		try {
			driver = new oracle.jdbc.driver.OracleDriver();// load Oracle Driver Software
			DriverManager.registerDriver(driver);// Registration of Driver
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "tiger");// Connection Establishment with DB Software
			if (con != null) {
				System.out.println("Connection Established!");
			} else {
				System.out.println("Connection Failed!");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (driver != null) {
				try {
					DriverManager.deregisterDriver(driver);
				} catch (SQLException se) {
					se.printStackTrace();
				}

			}
		} // end of finally
	}// end of main
}// end of class
