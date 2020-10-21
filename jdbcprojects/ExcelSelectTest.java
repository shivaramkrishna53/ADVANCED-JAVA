package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExcelSelectTest {
private static final String Query="select * from collegedataexcel.sheet1";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:Excel:///E:\\shiva java developer full stack"))
		{
			try(PreparedStatement ps=con.prepareStatement(Query))
			{
				try(ResultSet rs=ps.executeQuery())
				{
					while(rs.next())
					{
						System.out.println(rs.getString(1)+"  "+rs.getString(2)+" "+rs.getString(3));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
