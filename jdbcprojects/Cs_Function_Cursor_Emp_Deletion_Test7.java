package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Cs_Function_Cursor_Emp_Deletion_Test7 {
private static final String Emp_Query="{?=call fx_empdetails(?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		int id=0;
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.println("Enter the emp id number to fetch records and delete from database");
			id=sc.nextInt();
			}
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//getting the callable statement
			if(con!=null)
		    cs=con.prepareCall(Emp_Query);
		    //register the out params
			if(cs!=null)
			{
		    cs.registerOutParameter(1, Types.VARCHAR);
		    cs.registerOutParameter(3,OracleTypes.CURSOR);
		    //setting the input params
		    cs.setInt(2,id);
		    //execute the query
		    cs.execute();
		    //get the out params 
		   String res= cs.getString(1);
		   rs=(ResultSet)cs.getObject(3);
		   if(rs!=null)
		   {
			   if(rs.next())
				   System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4));
			   else
				   System.out.println("No records fetched with that id");
		   }
		   System.out.println("DELETION STATUS::"+ res);
			}
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()==1403)
				System.out.println("No dept exists with such details");
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


	}

}
