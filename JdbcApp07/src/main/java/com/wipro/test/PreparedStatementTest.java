package com.wipro.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementTest {

	private static final String QUERY = "update emp set sal=sal+(sal*?) where job in(?,?,?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		Scanner sc = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				pst = con.prepareStatement(QUERY);
			}
			if (pst != null) {
				sc = new Scanner(System.in);
				if (sc != null) {
					System.out.println("Salary hike percentage: ");
					float hike_percentage = sc.nextFloat();
					System.out.println("Employee Designation #1:");
					String desig1 = sc.next();
					System.out.println("Employee Designation #2:");
					String desig2 = sc.next();
					System.out.println("Employee Designation #3:");
					String desig3 = sc.next();

					pst.setFloat(1, hike_percentage);
					pst.setString(2, desig1);
					pst.setString(3, desig2);
					pst.setString(4, desig3);

					int res = pst.executeUpdate();
					if (res == 0) {
						System.out.println("Records not updated");
					} else {
						System.out.println("Records updated!!");
					}
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
				} catch (Exception se) {
					se.printStackTrace();
				}
			}

		} // end of final
	}// end of main

}// end of class
