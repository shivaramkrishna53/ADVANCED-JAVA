package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * version:2.0;
 * author:team_oracle;
 *  */
public class EmpHighestSal {

	//jdbc application for fetching highest salary of the employee
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables declaration
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//query:select * from emp where sal=(select max(sal) from emp);
		try
		{
		query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//creating connection object
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		if(con!=null)
			st=con.createStatement(); //creating statement object
		if(st!=null)
			rs=st.executeQuery(query);
		if(rs!=null)
		{
			if(rs.next())
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			else
				System.out.println("NO records Found");
		}//if
		
		
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(st!=null)
					st.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
			
		}
		
		


	}//main

}//class
