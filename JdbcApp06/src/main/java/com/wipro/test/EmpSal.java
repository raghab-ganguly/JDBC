/*Write a JDBC Application that gives employee details(empno,ename,job,sal) 
 * for the employee who is having highest salary and lowest salary
 */
package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpSal {
	public static void main(String[] args) {
		Connection con = null;
		Statement st1 = null;
		Statement st2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		String query1 = null;
		String query2 = null;
		try {
			// Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "system", "tiger");
			if (con != null) {
				st1 = con.createStatement();
				st2 = con.createStatement();
			}
			query1 = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			query2 = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			if (st1 != null) {
				System.out.println(query1);
				rs1 = st1.executeQuery(query1);
			}

			if (rs1 != null) {
				System.out.println("EMPNO\tENAME\tJOB\tMaxSAL\n==================================");
				while (rs1.next()) {
					System.out.println(
							rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getString(3) + "\t" + rs1.getInt(4));
				}
			}
			if (st2 != null) {
				System.out.println("\n" + query2);
				rs2 = st2.executeQuery(query2);
			}
			if (rs2 != null) {
				System.out.println("EMPNO\tENAME\tJOB\tMinSAL\n==================================");
				while (rs2.next()) {
					System.out.println(
							rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getInt(4));
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
			if (rs2 != null) {
				try {
					rs2.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (st2 != null) {
				try {
					st2.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (st1 != null) {
				try {
					st1.close();
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
