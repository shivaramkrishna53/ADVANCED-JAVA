package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteStudentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//delete from student where sid=101;
		//local variable declaration
		Scanner sc=null;
		int stuid=0;
		Connection con=null;
		Statement st=null;
		String query;
		int count=0;
		try
		{
		sc=new Scanner(System.in);
		if(sc!=null)
		{
			System.out.println("Enter the student id to be deleted:::");
			stuid=sc.nextInt();
			
			
		}
		//load Driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		//creating statement object
		if(con!=null)
		st=con.createStatement();
		//creating the query
		query="delete from student where sid="+stuid;
		//executing sql query
		if(st!=null)
		count=st.executeUpdate(query);
		if(count==0)
			System.out.println("NO records are deleted from database");
		else
			System.out.println(count + "  number of records got deleted from database");
		
		
		}
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
			try
			{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
