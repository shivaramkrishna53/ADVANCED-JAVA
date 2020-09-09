package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Product_infoUsingPostgresql {
private static final String Customer_Query="INSERT INTO PRODUCT_INFO VALUES(nextval('productid_seq'),?,?,?)"; //dynamic input values   ?-->called as params
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables declaration
		Scanner sc=null;
		String pname=null;
		float price=0.0f,qty=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try
		{
			//reading inputs from user
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter the productname::");
				pname=sc.next();
				System.out.println("Enter the product price::");
				price=sc.nextFloat();
				System.out.println("Enter the quantity");
				qty=sc.nextFloat();
			}
			
			
			//registering jdbc driver
			//Class.forName("org.postgresql.Driver");
			//Establishing the connection 
			 con=DriverManager.getConnection("jdbc:postgresql://localhost/advjavadb", "postgres", "shiva1234");
			//creating preparedstatement object
			if(con!=null)
			ps=con.prepareStatement(Customer_Query); //returning the jdbc preparedstatement object containg the pre compiled query
			
			//setting the input params for query
			if(ps!=null)
			{
				
					
					ps.setString(1,pname);
					ps.setFloat(2,price);
					ps.setFloat(3,qty);
			
					//executing the query
					result=ps.executeUpdate();
					
					if(result==0)
						System.out.println("Customer Details failed to insert");
					else
						System.out.println("Customer Details Successfully inserted");
					
					
				}
				}
			
			/*
			 * System.out.println(con.getClass()); System.out.println(ps.getClass());
			 */
			
			
			
			
		
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
