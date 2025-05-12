/*Write a JDBC Application to count no. of records
 * from Dept table.
 */
package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		int count = 0;
		try {
			// Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
			}
			query = "SELECT COUNT(*) FROM DEPT";
			if (st != null) {
				System.out.println(query);
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				rs.next();
				count = rs.getInt(1);
				System.out.println("Records count in Dept table: " + count);
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
		} // end of finally
	}// end of main
}// end of class
