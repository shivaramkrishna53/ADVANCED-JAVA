package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ParameterMetaData {
private static String insert_query="insert into emp(empno,ename,sal) values(?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234"))
		{
			try(PreparedStatement ps=con.prepareStatement(insert_query))
			{
				java.sql.ParameterMetaData pmd=ps.getParameterMetaData();
				int count=pmd.getParameterCount();
				System.out.println(count);
				for(int i=1;i<=count;i++)
				{
					System.out.println("Parameter number:::"+i);
					System.out.println("Parameter mode:::"+pmd.getParameterMode(i));
					System.out.println("Parameter type name::"+pmd.getParameterTypeName(i));
					System.out.println("Parameter scale::"+pmd.getScale(i));
					System.out.println("Parameter precision::"+pmd.getPrecision(i));
					System.out.println("---------------------------------------------------");
					
						
					
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
