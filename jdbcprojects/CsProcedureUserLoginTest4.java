package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureUserLoginTest4 {
private static final String login_query="{call LOGIN(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		String username=null,password=null;
		Connection con=null;
		CallableStatement cs=null;
		String result=null;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.println("Enter the username::");
			username=sc.next();
			System.out.println("Enter the password::");
			password=sc.next();
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234", "shiva1234");
			//get  the callable statement
			if(con!=null)
			cs=con.prepareCall(login_query);
			if(cs!=null)
			{
			//register the out params
			cs.registerOutParameter(3,Types.VARCHAR);
			//set input param values
			cs.setString(1,username);
			cs.setString(2,password);
			//execute query
			cs.execute();
			//get the output params
			result=cs.getString(3);
			System.out.println(result);
			}
			
			}
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()==1403)
				System.out.println("No Emp with such details");
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
			if(cs!=null)
				cs.close();
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


	}//main

}//class
