package com.raghab.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MavenTest {
	private static  String QUERY;
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		String uname=null;
		String upwd=null;
		try {
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//Autoloading Driver Class supported and Connection Establishment
			
			if(con != null) {
				st=con.createStatement();//Statement vehical object creation
			}
			
			if(st != null) {
				sc=new Scanner(System.in);
				System.out.print("Enter the UNAME: ");
				uname=sc.nextLine();
				System.out.print("Enter the PASS: ");
				upwd=sc.nextLine();
				QUERY="SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME= "+"'"+uname+"'"+" AND UPWD= "+"'"+upwd+"'";
				rs=st.executeQuery(QUERY);//send sql query to db software by Statement obj
			}
			System.out.println(QUERY);
			if(rs != null) {
				int count;
				rs.next();//process Query result
				count=rs.getInt(1);
				System.out.println("count= "+count);
				if(count == 0) {
					System.out.println("Invalid Credentials!!");
				}
				else {
					System.out.println("valid Credentials");
				}
			} 
		}catch (SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid table name or col name or keyword!!");
			}
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {                                 //close the resources
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(st!=null) {
				try {
					st.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(sc!=null) {
				try {
					sc.close();
				}catch(Exception se) {
					se.printStackTrace();
				}
			}
		}//end of finally
	}//end of main
}//end of class
