package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CsFunctionEmpTest6 {
private static final String empdetails_Query="{?=call FX_GETEMP(?,?,?)}";
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//local variables
			Scanner sc=null;
			int id=0;
			Connection con=null;
			CallableStatement cs=null;
	

			try
			{
				sc=new Scanner(System.in);
				if(sc!=null)
				{
					
					System.out.println("Enter the emp no:");
					id=sc.nextInt();
					
					//establish the connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##shiva1234", "shiva1234");
					//get callable statement object
					if(con!=null)
					cs=con.prepareCall(empdetails_Query);
					if(cs!=null)
					{
						//setting input values
						cs.setInt(2,id);
					//registering the out params
					cs.registerOutParameter(1,Types.FLOAT);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4,Types.VARCHAR);
					//execute the statement
					cs.execute();
					System.out.println(cs.getString(3)+"  "+cs.getString(4)+"  "+cs.getFloat(1));
					
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
			}


	}

}
