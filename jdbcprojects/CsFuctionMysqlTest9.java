package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFuctionMysqlTest9 {
private static final String Cust_Query="{?=call fx_cust_details(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables declaration
		Scanner sc=null;
		int id=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		try
		{
			sc=new Scanner(System.in);
			System.out.println("Enter the customer number:");
			id=sc.nextInt();
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjavadb", "root","shiva1234");
			//get the callable statement object
			if(con!=null)
			cs=con.prepareCall(Cust_Query);
			if(cs!=null)
			{
				//registering the out params
				cs.registerOutParameter(1,Types.FLOAT);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.VARCHAR);
				
				//setting the in params
				cs.setInt(2,id);
				//execute the query
				cs.execute();
				//getting the out params
				float bill=cs.getFloat(1);
				rs=cs.getResultSet();
				//retreving the result set object
				if(rs!=null)
				{
					if(rs.next())
					{
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+rs.getFloat(4));
						System.out.println("Bill is:"+ bill);
					}
					else
						System.out.println("No records found with that id");
				}
				
				
			}
			
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()==1403)
				System.out.println("No Emp with such details");
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
			if(cs!=null)
				cs.close();
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
