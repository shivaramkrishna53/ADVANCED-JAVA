package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Product_info_retrevingdata_usingpostgresql {
private static final String Select_productinfo_query="SELECT PID,PNAME,PRICE,QTY FROM PRODUCT_INFO";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring the local variables
		Scanner sc=null;
		int product_id=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int c=0;
		try
		{
			
			//creating the connection
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/advjavadb","postgres","shiva1234");
			//getting prepared statement object
			if(con!=null)
			ps=con.prepareStatement(Select_productinfo_query);
			
			
			//executing the prepared statement
			if(ps!=null)
			rs=ps.executeQuery();
			//retreving the result set items
			if(rs!=null)
			{
			while(rs.next())
			{
				c++;
				System.out.println("Product "+c+" details");
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getFloat(3));
				System.out.println(rs.getFloat(4));
				System.out.println("------------------------------------------------------");
			}
			if(c==0)
				System.out.println("NO records found");
			else
				System.out.println("All records fetched successfully");
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

	}

}
