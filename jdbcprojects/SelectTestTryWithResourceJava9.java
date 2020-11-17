package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestTryWithResourceJava9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234"))
		{
			
			Statement st=con.createStatement();
			try(st)
			{
				ResultSet rs=st.executeQuery("select empno,ename,sal,job from emp");
					try(rs)
					{
						if(rs!=null)
					while(rs.next())
					{
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getString(4));
						
					}
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

	}

}