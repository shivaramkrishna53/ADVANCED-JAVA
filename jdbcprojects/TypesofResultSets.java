package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Result;

public class TypesofResultSets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try
		{
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##shiva1234","shiva1234");
			//statement object
	       //st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); //sensitive rs
			//st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); //insensitve rs
	      // st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); // read only rs
	       st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=st.executeQuery("SELECT EMPNO,ENAME,JOB,SAL FROM EMP");
			if(rs!=null)
			{
				rs.absolute(1);
				rs.updateString(3,"cricketer");
				rs.updateInt(1,553);
				rs.updateRow();
				
				
				rs.absolute(4);
				rs.deleteRow();
				
				rs.moveToInsertRow();
				rs.updateInt(1,101);
				rs.updateString(2,"dhoni");
				rs.updateInt(4, 50000);
				rs.insertRow();
				rs.absolute(0);
				while(rs.next())
				{
//					if(count==0)
//					Thread.sleep(20000);
//					count++;
//					
//					rs.refreshRow(); //for sensitive rs rs.refresh row is compulsory
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}
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
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}


	}

}
