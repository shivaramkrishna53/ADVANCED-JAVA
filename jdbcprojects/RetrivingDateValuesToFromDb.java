package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class RetrivingDateValuesToFromDb {
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
				SimpleDateFormat sd1=new SimpleDateFormat("dd-MMM-yyyy");
				String dob=sd1.format(sqdob);
				Date sqdoj=rs.getDate(5);
				SimpleDateFormat sd2=new SimpleDateFormat("yyyy-dd-MM");
				String doj=sd2.format(sqdoj);
				Date sqdom=rs.getDate(6);
				SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MMM-dd");
				String dom=sdf3.format(sqdom);
				System.out.println("Result of default database date objects:::"+pid +" "+pname+" "+padd+" "+sqdob+" "+sqdoj+" "+sqdom);
				System.out.println("Result of converted SimpleDateFormat date objects to String:::"+pid +" "+pname+" "+padd+" "+dob+" "+doj+" "+dom);
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
