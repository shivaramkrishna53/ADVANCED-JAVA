package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CsvFileSelectTest {
private static final String query="select * from iplteams.csv";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection con=DriverManager.getConnection("jdbc:Text:///E:\\shiva java developer full stack"))
		{
			try(Statement st=con.createStatement())
			{
				try(ResultSet rs=st.executeQuery(query))
				{
					while(rs.next())
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
