package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



/*create or replace procedure p_empdetails(eno in number,ename out varchar2,ejob out varchar2,esal out number)as
begin
select e.ename,job,sal into ename,ejob,esal from emp e where empno=eno; 
end;
/*/


public class CsProcedureTest2 {
private static final String empdetails_Query="{call p_empdetails(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		int id=0,sal=0;
		String ename=null,ejob=null;
		Connection con=null;
		CallableStatement cs=null;

		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				
				System.out.println("Enter the emp id:");
				id=sc.nextInt();
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##shiva1234", "shiva1234");
				//get callable statement object
				if(con!=null)
				cs=con.prepareCall(empdetails_Query);
				if(cs!=null)
				{
				//registering the out params
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.NUMERIC);
				//set the input params
				cs.setInt(1,id);
				//executing the query
				cs.execute();
				ename=cs.getString(2);
				ejob=cs.getString(3);
				sal=cs.getInt(4);
				System.out.println(ename+ " "+ ejob+"  "+sal);
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
