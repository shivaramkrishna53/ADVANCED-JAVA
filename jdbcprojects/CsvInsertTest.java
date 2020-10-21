package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CsvInsertTest {
private static final String query="insert into iplteams.csv values('csk2','chennai','india','tamilnadu2')";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection con=DriverManager.getConnection("jdbc:Text:///E:\\shiva java developer full stack"))
		{
			try(Statement st=con.createStatement())
			{
				int count=st.executeUpdate(query);
				if(count==1)
					System.out.println("Successfully inserted into csv");
				else
					System.out.println("Failed to insert");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
