package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateJdbcEmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring local variables
		Scanner sc=null;
		String name=null,jb=null,query=null,jobincre=null;
		int sl=0,id=0,count=0;
		Connection con=null;
		Statement st=null;
		
		
		
		//query1
		// update emp set ename='sai',job='dasc',sal=90 where empno=7900;
		//query2
		//update emp set sal=1.2*sal where job='CLERK';
		try
		{
			//reading the input data
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				/*
				 * System.out.println("Enter the new ename::"); name=sc.next();
				 * System.out.println("Enter the new job::"); jb=sc.next();
				 * System.out.println("Enter the new sal::"); sl=sc.nextInt();
				 * System.out.println("Enter the employee id that u want to update with::");
				 * id=sc.nextInt();
				 */
				System.out.println("Enter the job for which the salary needs to be incremented:");
				jobincre=sc.next().toUpperCase();
			}
			/*
			 * //updating the variables according to the sql query name="'"+name+"'";//gives
			 * 'sai' jb="'"+jb+"'";//gives 'dasc' //framing the query i.e update emp set
			 * ename='sai',job='dasc',sal=100 where empid=7900;
			 * query="update emp set ename="+name+",job="+jb+",sal="+sl+" where empno="+id;
			 */
			//updating the variables according to the sql query
			
			jobincre="'"+jobincre+"'";
			
			//creating query   update emp set sal=1.2*sal where job='clerk';
			query="update emp set sal=1.2*sal where job="+jobincre;
			System.out.println(query);
			//loading the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//creating statement obj
			if(con!=null)
			st=con.createStatement();
			//executing the query
			if(st!=null)
				count=st.executeUpdate(query);
			if(count==0)
				System.out.println("No records found to update");
			else
				System.out.println(count+" number of records updated successfully");
			
			
			
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

	}

}
