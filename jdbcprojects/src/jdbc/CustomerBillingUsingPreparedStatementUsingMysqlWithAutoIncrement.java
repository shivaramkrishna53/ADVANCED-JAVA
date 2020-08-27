package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerBillingUsingPreparedStatementUsingMysqlWithAutoIncrement {
private static final String Customer_Query="INSERT INTO CUSTOMER(cname,cadd,cbill) VALUES(?,?,?)"; //dynamic input values   ?-->called as params
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables declaration
		Scanner sc=null;
		int cno=0,cbill=0,count=0,result=0;
		String cname=null,cadd=null;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			
			System.out.println("Enter how many customer details you want to enter::");
			count=sc.nextInt();
			}
			
			
			//registering Mysqljdbc driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Establishing the connection 
			//con=DriverManager.getConnection("jdbc:mysql:///advjavadb","root","shiva1234"); //if both the java application and mysql db are in same machine then use that url.
			con=DriverManager.getConnection("jdbc:mysql://localhost/advjavadb","root","shiva1234"); //if the java application and mysql db are in different machines then use that url.
			//creating preparedstatement object
			
			if(con!=null)
			ps=con.prepareStatement(Customer_Query); //returning the jdbc preparedstatement object containg the pre compiled query
			if(ps!=null)
			{
				//reading the inputs
				for(int i=1;i<=count;i++)
				{
				if(sc!=null)
				{
					//reading the inputs from end user
					
					System.out.println("Enter the"+i+" customer name:::");
					cname=sc.next();
					System.out.println("Enter the"+i+"customer address:::");
					cadd=sc.next();
					System.out.println("Enter the "+i+"customer bill amount:::");
					cbill=sc.nextInt();
					//setting the values to the jdbc preparedstatement object containing the pre compiled query
					
					ps.setString(1,cname);
					ps.setString(2,cadd);
					ps.setInt(3,cbill);
					//executing the query
					result=ps.executeUpdate();
					if(result==0)
						System.out.println(i+" :: Customer Details failed to insert");
					else
						System.out.println(i+" :: Customer Details Successfully inserted");
					
					
				}
				}
			}
			
			
			/*
			 * System.out.println(con.getClass()); System.out.println(ps.getClass());
			 */
			
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
				if(ps!=null)
					ps.close();
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
