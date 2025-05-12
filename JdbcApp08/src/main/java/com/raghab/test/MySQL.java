package com.raghab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
	private static final String SELECT_QUERY = "SELECT * FROM STUDENT";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//con = DriverManager.getConnection("jdbc:mysql:///raghab", "root", "root");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/raghab","root","root");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/raghab","root","root");
			System.out.println(con.getClass().getName());
			if (con != null) {
				pst = con.prepareStatement(SELECT_QUERY);
			}
			if (pst != null) {
				rs = pst.executeQuery();
			}
			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getString(1) + "\t" + rs.getString(2));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}