package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmpInsertTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insert into emp(empno,ename,job,sal) values(101,'sai','datasc',20);
		//declaration of local variables
		int eno=0,esal=0;
		String ename=null,ejob=null;
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		//reading input values from user
		try
		{
		sc=new Scanner(System.in);
		if(sc!=null)
		{
		System.out.println("Enter the empno::");
		eno=sc.nextInt();
		System.out.println("Enter the ename::");
		ename=sc.next();
		System.out.println("Enter the ejob::");
		ejob=sc.next();
		System.out.println("Enter the esal::");
		esal=sc.nextInt();
		}
		//load driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		//creating statement obj
		if(con!=null)
			st=con.createStatement();
		//modifying the input values to match the sql query
		//insert into emp(empno,ename,job,sal) values(101,'sai','datasc',20);
		ename="'"+ename+"'";  //i.e 'sai'
		ejob="'"+ejob+"'";   //i.e 'datsc'
		query="insert into emp(empno,ename,job,sal) values("+eno+","+ename+","+ejob+","+esal+")";
		System.out.println(query);
		//execute the sql query
		count=st.executeUpdate(query);
		if(count==0)
			System.out.println("no records are inserted in database");
		else
			System.out.println(count + "no of records inserted in database");
		

	}
		catch(SQLException se)
		{
			System.out.println(se.getErrorCode()); // getErrorCode gives the error code number of the exception
			if(se.getErrorCode()==12899)
				System.out.println("The charcters are too large for column");
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("sql query syntax incorrect");
			
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
		}

}
}
