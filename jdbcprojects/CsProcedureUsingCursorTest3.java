package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;



/*CREATE OR REPLACE PROCEDURE EMP_DETAILS_BYSTARTNAME 
(
  NAME IN VARCHAR2 
, EMP_DETAILS OUT SYS_REFCURSOR 
) AS 

BEGIN
OPEN EMP_DETAILS FOR
SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE '+NAME+';

END;
/*/


public class CsProcedureUsingCursorTest3 {
private static final String empdetails_Query="{call EMP_DETAILS_BYSTARTNAME(?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		int id=0,sal=0;
		String ename=null,ejob=null,startstring=null,tot_str=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;

		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				
				System.out.println("Enter the emp initial characters:");
				startstring=sc.next();
				tot_str="'"+startstring+"%"+"'";
				System.out.println(tot_str);
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##shiva1234", "shiva1234");
				//get callable statement object
				if(con!=null)
				cs=con.prepareCall(empdetails_Query);
				if(cs!=null)
				{
					//setting input values
					cs.setString(1,tot_str);
				//registering the out params
				cs.registerOutParameter(2,OracleTypes.CURSOR);
				//execute the statement
				cs.execute();
				//retreving the result set obj
				rs=(ResultSet)cs.getObject(2);
				
				if(rs!=null)
				{
					while(rs.next())
					{
						System.out.println("hii");
						System.out.println(rs.getInt(1)+ "  "+ rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
					}
				}
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
		}

	}

}
