package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureDeptTest5 {
private static final String DEPT_QUERY="{call DEPT_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		int dno=0;
		Connection con=null;
		CallableStatement cs=null;
		try
		{
			System.out.println("Enter the deptno::");
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				dno=sc.nextInt();
			}
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//get the callable statement
			cs=con.prepareCall(DEPT_QUERY);
			//registering the outparams
			cs.registerOutParameter(2,Types.VARCHAR);
			cs.registerOutParameter(3,Types.VARCHAR);
			//setting values to in params
			cs.setInt(1,dno);
			//execute the query
			cs.execute();
			//get the out params
			System.out.println(cs.getString(2)+"  "+cs.getString(3));
			
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
