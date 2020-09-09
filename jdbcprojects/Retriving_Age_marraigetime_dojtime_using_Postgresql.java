package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Retriving_Age_marraigetime_dojtime_using_Postgresql {
private static final String retrive_dates_query="SELECT * FROM PERSON_DETAILS WHERE PID=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring local variables
		Scanner sc=null;
		int id=0;
		String name=null;
		PreparedStatement ps=null;
		Connection con=null;
		ResultSet rs=null;
		String dob=null,doj=null,dom=null;
		
		
		try
		{
			//reading the user id to retrive the details
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("ENTER THE PERSON ID TO RETRIVE DATA::");
				id=sc.nextInt();
				
			}
			//establish the  connection
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/advjavadb","postgres","shiva1234");
			//creating Prepared statement object
			if(con!=null)
				ps=con.prepareStatement(retrive_dates_query);
			//setting the input params for the query
			if(ps!=null)
				ps.setInt(1, id);
			//executing the query
			if(ps!=null)
				rs=ps.executeQuery();
			//retreving the data from the result set
			if(rs!=null)
			{
				if(rs.next())
				{
					id=rs.getInt(1);
					name=rs.getString(2);
					java.sql.Date sqldob=rs.getDate(3);
					java.sql.Date sqldoj=rs.getDate(4);
					java.sql.Date sqldom=rs.getDate(5);
					//converting them into String
					SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					dob=sdf.format(sqldob);
					doj=sdf.format(sqldoj);
					dom=sdf.format(sqldom);
					System.out.println(id+" "+name+" "+dob+" "+doj+" "+dom);
					//calculating the age of the person
					java.util.Date d=new java.util.Date();
					long presentday=d.getTime();
					long dobday=d.getTime();
					long diff=presentday-dobday;
					float age=diff/(1000*60*60*24*365.25f);
					System.out.println("Person age is:"+age);
					
				}
				else
					System.out.println("No records present with such id");
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
