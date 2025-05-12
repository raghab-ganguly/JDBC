//Testing with PreparedStatement obj and execute() method for both Select and non-select query
package com.raghab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatement_ExecuteTest {
	private static String SQL_QUERY;

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Scanner sc = null;
		int count = 0;
		int check=0;
		boolean flag=false;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");//Connection Establishment
			sc = new Scanner(System.in);
			do {
				if (sc != null) {
					System.out.print("Enter any SQL Qurery: ");//taking query from user
					SQL_QUERY = sc.nextLine();
				}
				System.out.println("You Entered ->  " + SQL_QUERY);
				if (con != null) {
					pst = con.prepareStatement(SQL_QUERY);// precompiled SQL Qurery 
				}
				if (pst != null) {
					flag = pst.execute();
					if (flag) {
						System.out.println("It is a Select Qurery");
						rs = pst.getResultSet();
					} else {
						System.out.println("It is a Non-Select Qurery");
						count = pst.getUpdateCount();
					}
				}
				if(flag) {
					if (rs != null) {
						boolean next=false;
						while(rs.next()) {
								next =true;
								System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
						}
						if(next==false) {
							System.out.println("No Records Found!!!");
						}
					}
				}
				else {
					System.out.println(count + " no. of Rows Updated");
				}
				System.out.print("Do u want to perform operation on DB again press 1 or For Exit press 0: ");
				check=sc.nextInt();
				sc.nextLine();
			}while(check == 1);
		} catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
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
			if (pst != null) {
				try {
					pst.close();
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
}// end class