//Write a JDBC Application to fetch the person details according to given starting date and ending date
package com.raghab.test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class DateHandlingTest {
	private final static String SELECT_DATE_QUERY="SELECT EMPNO,ENAME,JOB,HIREDATE,SAL FROM EMP WHERE HIREDATE>=(?) AND HIREDATE<=(?)";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String startDate=null;
		String endDate=null;
		FileInputStream fis=null;
		Properties pr=null;
		OracleConnectionPoolDataSource ds=null;
		String url=null;
		String user=null;
		String password=null;
		SimpleDateFormat sdf=null;
		java.util.Date ud1,ud2=null;
		java.sql.Date sqlDate1,sqlDate2=null;
		
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");                                                                    // load driver
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger"); // Registration(Internally) and connection
			
			fis=new FileInputStream("src/main/resources/db.properties"); 
			pr=new Properties();
			pr.load(fis);                     //fetching connection url,user,password from file
			url=pr.getProperty("url");
			user=pr.getProperty("user");
			password=pr.getProperty("password");
			
			ds=new OracleConnectionPoolDataSource();
			ds.setURL(url);
			ds.setUser(user);
			ds.setPassword(password);
			con=ds.getConnection();   //Connetion Establishment using Connetion pooling methodology
			
			sc=new Scanner(System.in);
			if(sc!= null) {
				System.out.print("Enter the starting range of HireDate(DD-MM-YYYY): ");
				startDate=sc.next();
				System.out.print("Enter the ending range of HireDate(DD-MM-YYYY): ");
				endDate=sc.next();
			}
			sdf=new SimpleDateFormat("dd-MM-yyyy"); //converting String date value to java.util.Date date value
			ud1=sdf.parse(startDate);
			ud2=sdf.parse(endDate);
			
			long l1=ud1.getTime();
			long l2=ud2.getTime();
			sqlDate1=new java.sql.Date(l1); //converting java.util.Date date value to java.sql.Date date value
			sqlDate2=new java.sql.Date(l2);
			
			System.out.println("SELECT EMPNO,ENAME,JOB,HIREDATE,SAL FROM EMP WHERE HIREDATE>= "+sqlDate1+" AND HIREDATE<= "+sqlDate2);
			if(con != null) {
				pst=con.prepareStatement(SELECT_DATE_QUERY); //precompiled SQL qurery
			}
			if(pst != null) {
				pst.setDate(1, sqlDate1);
				pst.setDate(2, sqlDate2);
				rs=pst.executeQuery(); //sending the SQL query to the DB software
			}
			if(rs!=null) {
				boolean flag=true;
				System.out.println("EMPNO \t ENAME \t JOB \t HIREDATE \t SAL \n======================================================");
				while(rs.next()) {
					flag=false;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDate(4)+"\t"+rs.getDouble(5));
				}
				if(flag) {
					System.out.println("No Records found !!!");
				}
			}
			
		} catch(SQLException se) {
			if(se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid Column name or table name or keyword !!!");
			}
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if(sc!=null) {
				try {
					sc.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (Exception se) {
					se.printStackTrace();
				}
			}
		}//end of finally

	}//end of main

}//end of class
