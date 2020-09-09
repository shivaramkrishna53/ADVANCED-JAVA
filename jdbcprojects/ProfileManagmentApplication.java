package jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class ProfileManagmentApplication {
private static final String INSERT_INTO_PROFILEMANAGEMENT_QUERY="INSERT INTO PROFILEMANAGMENTAPPLICATION(JOBSEEKERNAME,JOBSEEKERADDRESS,PHOTO,VIDEO,RESUME) VALUES(?,?,?,?,?)";
private static final String Retrive_From_PROFILEMANAGEMENT_QUERY="SELECT JOBSEEKERID,JOBSEEKERNAME,JOBSEEKERADDRESS,PHOTO,VIDEO,RESUME FROM PROFILEMANAGMENTAPPLICATION WHERE JOBSEEKERID=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables declaration
		int ch=0;
		Scanner sc=null;
		String name=null,address=null,photolocation=null,videolocation=null,resumelocation=null;
		InputStream photois=null,videois=null;
		OutputStream photoOs=null,videoOs=null;
		Reader r=null;
		Writer w=null;
		Connection con=null;
		PreparedStatement ps=null;
		int id=0;
		ResultSet rs=null;
		try
		{
		
		System.out.println("WELCOME TO NAUKIRI.COM");
		System.out.println("MENU:::::::::::::");
		System.out.println("1.REGISTERING INTO NAUKIRI.COM");
		System.out.println("2.TO VIEW YOUR PROFILE");
		System.out.println("ENTER YOUR CHOICE(1 OR 2):::");
		sc=new Scanner(System.in);
		if(sc!=null)
		ch=sc.nextInt();
		
		if(ch==1)
		{
			if(sc!=null)
			{
			System.out.println("YOU ARE INTO REGISTERING PROFILE::");
			System.out.println("ENTER THE NAME OF THE JOB SEEKER");
			name=sc.next();
			System.out.println("ENTER THE ADDRESS OF THE ADDRESS OF THE JOB SEEKER");
			address=sc.next();
			System.out.println("ENTER THE PHOTO LOCATION OF THE JOB SEEKER::");
			photolocation=sc.next();
			System.out.println("ENTER THE VIDEO LOCATION OF THE JOB SEEKER::");
			videolocation=sc.next();
			System.out.println("ENTER THE RESUME LOCATION OF THE JOB SEEKER::");
			resumelocation=sc.next();
			}
			// creating the inputstream,reader
			photois=new FileInputStream(photolocation);
			videois=new FileInputStream(videolocation);
			r=new FileReader(resumelocation);
			//creating the jdbc connection
			con=DriverManager.getConnection("jdbc:mysql:///advjavadb","root","shiva1234");
			//creating jdbc statement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_INTO_PROFILEMANAGEMENT_QUERY);
			//setting the params values prepared statment...ps contains the pre compiled query and we need to set the values
			if(ps!=null)
			{
				ps.setString(1,name);
				ps.setString(2,address);
				ps.setBinaryStream(3,photois);
				ps.setBinaryStream(4,videois);
				ps.setCharacterStream(5,r);
				//executing the prepared statment
				int count=ps.executeUpdate();
				if(count==0)
					System.out.println("Failed to insert the record in naukiri");
				else
					System.out.println("Successfully inserted the record in naukiri");
			}
					
		}
		else
			if(ch==2)
			{
				System.out.println("YOU ARE INTO RETREVING/VIEWING THE PROFILE");
				System.out.println("ENTER THE JOBSEEKER ID::");
				id=sc.nextInt();
				//creating jdbc connection
				con=DriverManager.getConnection("jdbc:mysql:///advjavadb","root","shiva1234");
				//creating jdbc prepared statement object
				ps=con.prepareStatement(Retrive_From_PROFILEMANAGEMENT_QUERY);
				//setting the ps input params
				if(ps!=null)
				{
					ps.setInt(1,id);
				//executing the prepared statement and storing in result set
					rs=ps.executeQuery();
				}
				//retreving the result set
				if(rs!=null)
				{
					if(rs.next())
					{
						id=rs.getInt(1);
						name=rs.getString(2);
						address=rs.getString(3);
						photois=rs.getBinaryStream(4);
						videois=rs.getBinaryStream(5);
						r=rs.getCharacterStream(6);
						//creating ouput streams and writers to write the data to specific location
						 photoOs=new FileOutputStream("naukiri_userphoto");
						 videoOs=new FileOutputStream("naukiri_uservideo");
						 w=new FileWriter("naukiri_userresume");
						//copying the input data
						if(photois!=null && videois!=null && r!=null)
						{
						IOUtils.copy(photois, photoOs);
						IOUtils.copy(videois, videoOs);
						IOUtils.copy(r, w);
						System.out.println("Data succesfully retrived");
						}
						System.out.println(id+"  "+name+"  "+address);
					}
					else
						System.out.println("NO RECORDS FOUND WITH THAT ID");
				}
			}
			else
				System.out.println("INVALID OPTION SELECTION");
		
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
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
				if(photois!=null)
					photois.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(videois!=null)
					videois.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(r!=null)
					r.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(photoOs!=null)
					photoOs.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(videoOs!=null)
					videoOs.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(w!=null)
					w.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
