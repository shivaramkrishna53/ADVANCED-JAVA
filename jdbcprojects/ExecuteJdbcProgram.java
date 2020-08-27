package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExecuteJdbcProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//local variables declaration
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		boolean check=false;
		int count=0;
		
		try
		{
		 sc=new Scanner(System.in);
		 System.out.println("Enter the query::");
		 if(sc!=null)
		 query=sc.nextLine(); 
		 //loading driver class
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //establish the connection
		 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
		 //creating statement object
		 if(con!=null)
			 st=con.createStatement();
		 if(st!=null)
		 check=st.execute(query);
		 
		 
		 if(check==true)
		 {
			 System.out.println("Select SQL query is executed");
			rs=st.getResultSet();
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
					flag=true;
				}
			}
			if(flag)
				System.out.println("Records found");
			else
				System.out.println("NO Records found");
		 }
		 else
		 {
			 System.out.println("NON SQL query is executed");
			 count=st.getUpdateCount();
			 if(count==0)
				 System.out.println("No records found");
			 else
				 System.out.println(count+" Number of records updated");
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(st!=null)
					st.close();
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

}//main
}//class
