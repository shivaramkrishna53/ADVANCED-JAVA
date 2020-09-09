package jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBlogRetrivingImageandVideo {
private static final String Blog_retriving_Query="SELECT PERSONID,NAME,SALARY,PHOTO,VIDEO FROM MATRIMONY_INFO WHERE PERSONID=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Declaring local Variables
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int pid=0;
		Scanner sc=null;
		InputStream photoIS=null;
		InputStream videoIS=null;
		OutputStream photoOs=null;
		OutputStream videoOs=null;
		try
		{
			sc=new Scanner(System.in);
			//reading the inputs
			if(sc!=null)
			{
				System.out.println("Enter the person id to get details::");
				pid=sc.nextInt();
			}
			//get connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234", "shiva1234");
			//get prepared statement object
			if(con!=null)
				ps=con.prepareStatement(Blog_retriving_Query);
			//setting the inputs to the params 
			if(ps!=null)
			{
				ps.setInt(1,pid);
			}
			//executing the prepared statement
			rs=ps.executeQuery();
			//processing the resultset obj
			if(rs!=null)
			{
				if(rs.next())
				{
					int perid=rs.getInt(1);
					String pname=rs.getString(2);
					float salary=rs.getFloat(3);
					photoIS=rs.getBinaryStream(4);
					videoIS=rs.getBinaryStream(5);
					//creating output streams
					photoOs=new FileOutputStream("D:\\roopesh_folder\\personphoto.jpg");
					videoOs=new FileOutputStream("D:\\roopesh_folder\\personvideo.mp4");
					if(photoIS!=null && videoIS!=null)
					{
						IOUtils.copy(photoIS,photoOs);
						IOUtils.copy(videoIS, videoOs);
					}
					
					
					System.out.println(perid+" "+pname+" "+salary);
				}
				else
					System.out.println("No records found");
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
				if(photoIS!=null)
					photoIS.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if(videoIS!=null)
					videoIS.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
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
		}//finally
		

	}

}
