package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableResultSetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try
		{
			//establish the  connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
		//get the statement obj
		st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs=st.executeQuery("select * from emp");
		if(rs!=null)
		{
			rs.next();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.previous();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.first();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.last();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.beforeFirst();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.afterLast();
			System.out.println("current rs pointer position::::"+rs.getRow());
			rs.beforeFirst();
			System.out.println("-----------------------------------------------------");
			while(rs.next())
			{
				System.out.println("current rs pointer position:::"+rs.getRow());
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			System.out.println("---------------------------------------------------------");
			while(rs.previous())
			{
				System.out.println("current rs pointer position:::"+rs.getRow());
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			System.out.println("-----------------------------------------------------------");
			rs.first();
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(3);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(-2);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(7);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.absolute(5);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.absolute(9);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(-4);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(6);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.absolute(-5);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.absolute(-10);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.absolute(100);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			rs.relative(200);
			System.out.println("current resultset pointer position:::"+rs.getRow());
			
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
			
		}
	}

}
