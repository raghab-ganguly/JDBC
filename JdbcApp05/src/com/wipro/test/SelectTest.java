/*Assignment #5: Write a JDBC Application to get DEpartment details based on
 * given Dept based on given Dept No. by following real time coding standards.
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
		int dno = 0;
		String query = null;
		try {
			//Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
			}
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.print("Enter Department No. : ");
				dno = sc.nextInt();
				query = "SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO=" + dno;
			}
			if (st != null) {
				System.out.println(query);
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				if (rs.next()) {
					System.out.println("DEPTNO\tDNAME\tLOC\n===============================");
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
				} else {
					System.out.println("NO Records Found!!!");
				}
			}
		} catch (SQLException se) {
			if (se.getErrorCode() <= 900 && se.getErrorCode() >= 999) {
				System.out.println("Invalid col name or table name or keyword!!!");
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // end of finally
	}// end of main
}// end of class