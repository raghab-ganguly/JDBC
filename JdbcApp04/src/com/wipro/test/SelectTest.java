/*Write a JDBC Application to display empno,ename,job and sal col values from emp table 
 * based on given salary range by following real time coding standards.
 */
package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = null;
		double salLow = 0.0;
		double salHigh = 0.0;
		String query = null;
		boolean flag = true;
		try {
			sc = new Scanner(System.in);
			//Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
			}
			if (sc != null) {
				System.out.print("Enter the lowest salary: ");
				salLow = sc.nextDouble();
				System.out.print("Enter the highest salary: ");
				salHigh = sc.nextDouble();
				query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL" + ">=" + salLow + " AND " + "SAL" + "<="
						+ salHigh;
			}
			if (st != null) {
				System.out.println(query);
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				System.out.println("EMPNO\tENAME\tJOB\tSAL\n=======================================");
				while (rs.next()) {
					flag = false;
					System.out.println(rs.getString("EMPNO") + "\t" + rs.getString("ENAME") + "\t" + rs.getString("JOB")
							+ "\t" + rs.getString("SAL"));
				}
				if (flag) {
					System.out.println("NO Records found!!!");
				}
			}
		} catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid Col name or table name or keyword!!!");
			}
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (sc != null) {
				try {
					sc.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
		} // end of finally
	}// end of main
}// end of class
