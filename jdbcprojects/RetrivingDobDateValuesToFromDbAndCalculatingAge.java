package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RetrivingDobDateValuesToFromDbAndCalculatingAge {
private static final String select_date_values_query="select pid,pname,padd,pdob,pdoj,pdom from person_date_details where pid=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring local variables
		Scanner sc=null;
		int id=0;
		
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;
		
		
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.println("Enter the person id to get the details::");
			id=sc.nextInt();
			//registering oracle jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//registering mysql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//getting oracle jdbc connection
		// con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//getting mysql jdbc connection
			con=DriverManager.getConnection("jdbc:mysql://@localhost:3306/advjavadb","root","shiva1234");
			//getting prepared statement object
		 ps=con.prepareStatement(select_date_values_query);
			//setting the query params
			if(ps!=null)
			ps.setInt(1,id);
			//execute the prepared statement
			 rs=ps.executeQuery();
			if(rs.next())
			{
				int pid=rs.getInt(1);
				String pname=rs.getString(2);
				String padd=rs.getString(3);
				Date sqdob=rs.getDate(4);
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
				String ddob=sdf.format(sqdob);
				java.util.Date predt=new java.util.Date();
				long finms=predt.getTime()-sqdob.getTime();
				System.out.println("Person id is::"+pid);
				System.out.println("Person name is::"+pname);
				System.out.println("Person address is::"+padd);
				System.out.println("Person Date of birth is::"+ddob);
				System.out.println("Person age is::"+ finms/3.154e+10 +" years");
			}
			else
			{
				System.out.println("No records found with that id");
			} 
					
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
