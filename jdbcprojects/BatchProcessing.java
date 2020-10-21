package jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BatchProcessing {

	public static void main(String[] args) {
		//establish the connection
		//get the properties file input 
		
		
			try(InputStream is=new FileInputStream("src/com/nt/commons/jdbc.properties"))
			{
			//creating properties object
			Properties props=new Properties();
			//load the file into props object
			props.load(is);
			try(Connection con=DriverManager.getConnection(props.getProperty("url"),props.getProperty("username"),props.getProperty("password")))
			{
			//get statement object
			try(Statement st=con.createStatement())
			{
			//adding the batch querys
			st.addBatch("insert into emp(empno,ename,sal) values(92,'suresh',20)");
			st.addBatch("update dept set loc='hyderabad' where deptno>20");
			st.addBatch("delete from student where sid=101");
			int results[]=st.executeBatch();
			for(int i:results)
				System.out.println(i);
			}//try3
			}//try2
			}//try1
			
			
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}//main

}//class
