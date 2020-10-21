package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//establish the connection
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234"))
		{
			if(con!=null)
				try(Statement st=con.createStatement())
				{
					if(st!=null)
						try(ResultSet rs=st.executeQuery("select empno,ename,job,sal from emp"))
						{
							ResultSetMetaData rsmd=null;
							if(rs!=null)
								rsmd=rs.getMetaData();
							int count=rsmd.getColumnCount();
							System.out.println("No. of colomns present in table::"+rsmd.getColumnCount());
							for(int i=1;i<=count;i++)
							{
								System.out.print(rsmd.getColumnName(i)+"  ");
								
							}
							System.out.println();
							for(int i=1;i<=count;i++)
							{
								System.out.print(rsmd.getColumnTypeName(i)+"  ");
							}
							while(rs.next())
							{
								for(int i=1;i<=rsmd.getColumnCount();i++)
								{
									System.out.print(rs.getString(i)+" ");
								}
								System.out.println();
							}
							for(int i=1;i<=rsmd.getColumnCount();++i) {
					    		System.out.println("col index ::"+i);
					    		System.out.println("col name::"+rsmd.getColumnLabel(i));
					    		System.out.println("col data type name::"+rsmd.getColumnTypeName(i));
					    		System.out.println("col scale ::"+rsmd.getScale(i));
					    		System.out.println("col precision ::"+rsmd.getPrecision(i));
					    		System.out.println("col is nullable?"+rsmd.isNullable(i));
					    		System.out.println("col is case-sensitive?"+rsmd.isCaseSensitive(i));
					    		System.out.println("col is AutoIncrement?"+rsmd.isAutoIncrement(i));
					    		System.out.println("--------------------------");
					    	}//for
						}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}
