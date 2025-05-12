/*
 * Assignmnet #1:  Write a JDBC Application to test the connection.
*/
package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "system", "tiger");
			if (con != null) {
				System.out.println("Connection Established!");
			} else {
				System.out.println("Connection NOtEstablished!");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}//end of finally
	}//end of main
}//end of class
