package jdbc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsClobRetrevingData {
private static final String Clob_Retreving_Query="SELECT * FROM NAUKIRI WHERE USERID=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//local variables declaration
		Scanner sc=null;
		
		String name=null,address=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader r=null;
		Writer w=null;
		ResultSet rs=null;
		int uid=0;
		//reading the input data from the user
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter the userid::");
				uid=sc.nextInt();
				
			}
			//prepare the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//prepare the statement object
			if(con!=null)
			ps=con.prepareStatement(Clob_Retreving_Query);
			
			if(ps!=null)
			{
				ps.setInt(1,uid);
				rs=ps.executeQuery();
			}
			//creating the reader and wirter
			 w=new FileWriter("resume.txt");
			
			//retreving the result set object
			if(rs!=null)
			{
				if(rs.next())
				{
					uid=rs.getInt(1);
					name=rs.getString(2);
					address=rs.getString(3);
					r=rs.getCharacterStream(4);
					if(r!=null && w!=null)
					{
						IOUtils.copy(r,w);
						System.out.println("resume succesfully copied");
						System.out.println(uid+"  "+name+"  "+address);
					}
					
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
				if(r!=null)
					r.close();
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
		

	}

}
