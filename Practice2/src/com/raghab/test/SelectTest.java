package com.raghab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {
	private static String QUERY;

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// Load implemented driver class from ojdbc8.jar
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");// Registration
																											// and
																											// Connection
																											// with DB

			if (con != null) {
				st = con.createStatement();// Vehical object creation of Statement type
			}
			if (st != null) {
				sc=new Scanner(System.in);
				System.out.print("Enter any starting keyword that of JOB : ");
				QUERY = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB LIKE " + "'" + sc.nextLine().toUpperCase() + "%'";
				rs = st.executeQuery(QUERY);
			}
			System.out.println(QUERY);
			if (rs != null) {
				System.out.println("EMPNO \t ENAME \t JOB \t SAL \n======================================");
				while (rs.next()) {
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDouble(4));
				}
			} else {
				System.out.println("No Records Found!!!");
			}

		} catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid keyword or col name or table name!!");
			}
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (sc != null) {
				try {
					sc.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} // end of finally
	}// end of main
}// end of class
