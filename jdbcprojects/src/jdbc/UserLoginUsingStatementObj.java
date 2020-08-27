package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//userlogin using statement object
public class UserLoginUsingStatementObj {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		String username=null,password=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int result=0;
		try
		{
		sc=new Scanner(System.in);
		if(sc!=null)
		{
			System.out.println("Enter the username::");
			username=sc.nextLine();
			System.out.println("Enter the password::");
			password=sc.nextLine();
			
			
		}
		//registering  jdbc object
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//creating jdbc connection object
		con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","c##shiva1234","shiva1234");
		//creating statement object
		if(con!=null)
		st=con.createStatement();
		//preparing the query that needs to be executed in database
		// select count(*) from userlogin where username='shiva' and password='shiva1234'
		//modifying the input stored variables according to the query
		username="'"+username+"'";//'shiva'
		password="'"+password+"'";//'shiva1234'
		//query framing
		query="select count(*) from userlogin where username="+username+" and password="+password;
		//execute the query
		if(st!=null)
			rs=st.executeQuery(query);
		if(rs!=null)
		{
			rs.next();
			result=rs.getInt(1);
			if(result==0)
				System.out.println("Invalid credentials:::Unable to login user");
			else
				System.out.println("Valid credentials:::User Login successfull");
			
			
		}
		
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//closing the jdbc objects connections
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
