package jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlogInsertImageAndVideoMatrimony {
private static final String Insert_into_Matrimony_query="INSERT INTO MATRIMONY_INFO VALUES(PERSONID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		String name=null,photoloc=null,videoloc=null;
		float salary=0;
		PreparedStatement ps=null;
		Connection con=null;
		InputStream photoIS=null;
		InputStream videoIs=null;
		try
		{
			sc=new Scanner(System.in);
			//reading the inputs from user
			if(sc!=null)
			{
				System.out.println("Enter Matrimony person name::");
				name=sc.next();
				System.out.println("Enter the person salary::");
				salary=sc.nextFloat();
				System.out.println("Enter the person photo location::");
				photoloc=sc.next();
				System.out.println("Enter the person video location::");
				videoloc=sc.next();
				
			}
			//registering driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//getting jdbc connection object
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//getting prepared statement object
			if(con!=null)
			 ps=con.prepareStatement(Insert_into_Matrimony_query);
			//creating InputStream object
			photoIS=new FileInputStream(photoloc);
			videoIs=new FileInputStream(videoloc);
			//inserting the values to the prepared statement
			if(ps!=null)
			{
				ps.setString(1,name);
				ps.setFloat(2,salary);
				ps.setBinaryStream(3,photoIS); // we can also use ps.setBlob(parameterIndex, inputstream obj);
				ps.setBinaryStream(4,videoIs);
			}
			//executing the prepared statement 
			int count=ps.executeUpdate();
			if(count==0)
				System.out.println("Failed to insert the record into matrimony table");
			else
				System.out.println("Successfully inserted into matrimony table");
			
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
			try
			{
				if(photoIS!=null)
					photoIS.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(videoIs!=null)
					videoIs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//finally
		
		}//main

	}//class


