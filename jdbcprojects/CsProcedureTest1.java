package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CsProcedureTest1 {
private static final String SUM_Query="{call p_sum(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring local variables
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int x=0,y=0,z=0;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.println("Enter the x:");
			x=sc.nextInt();
			System.out.println("Enter the y:");
			y=sc.nextInt();
			}
			//establishn connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//getting CallableStatement object
			cs=con.prepareCall(SUM_Query);
			//registering out params
			cs.registerOutParameter(3,Types.INTEGER);
			//setting input values to inparams
			cs.setInt(1,x);
			cs.setInt(2, y);
			//executing the query
			cs.execute();
			z=cs.getInt(3);
			System.out.println("sum of x and y is:"+z);
			
			
			
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
