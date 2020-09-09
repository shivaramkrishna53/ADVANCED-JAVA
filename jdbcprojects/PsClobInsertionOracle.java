package jdbc;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsertionOracle {
private static final String Clob_insert_Query="INSERT INTO NAUKIRI VALUES(NAUKIRI_USERID.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//local variables declaration
		Scanner sc=null;
		int count=0;
		String name=null,address=null,resumelocation=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader r=null;
		//reading the input data from the user
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter the job seeker name::");
				name=sc.next();
				System.out.println("Enter the job seeker address::");
				address=sc.next();
				System.out.println("Enter the resume location::");
				resumelocation=sc.next();
				
			}
			//prepare the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//prepare the statement object
			if(con!=null)
			ps=con.prepareStatement(Clob_insert_Query);
			//reader object
			r=new FileReader(resumelocation);
			//set the params for the query
			ps.setString(1,name);
			ps.setString(2,address);
			ps.setCharacterStream(3, r);
			//execute result set
			count=ps.executeUpdate();
			if(count==0)
				System.out.println("Record failed to insert");
			else
				System.out.println("successfully inserted into db");
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
		}//finally
		

	}

}
