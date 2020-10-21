package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DataBaseMetaDataTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234"))
		{
			DatabaseMetaData dbmd=con.getMetaData();
			System.out.println("DATABASE NAME::"+dbmd.getDatabaseProductName()); //getDataBaseProductName
			System.out.println("DATABASE VERSION::"+dbmd.getDatabaseMajorVersion()+"."+dbmd.getDatabaseMinorVersion()); //getDataBaseMajorVersion //getDatabaseMinorVersion
			System.out.println("DATABASE COMPLETE NAME WITH VERSION::"+dbmd.getDatabaseProductVersion());  //dbmd.getDataBaseProductVersion
			System.out.println("max row size::"+dbmd.getMaxRowSize());
			System.out.println("max colmns in db table::"+dbmd.getMaxColumnsInTable());
			System.out.println("Max cols in select SQL query::"+dbmd.getMaxColumnsInSelect());
			System.out.println("supports pl/sql procedures::"+dbmd.supportsStoredProcedures());
			System.out.println("all numeric funtions::"+dbmd.getNumericFunctions());
			System.out.println("All sql keywords::"+dbmd.getSQLKeywords());
			System.out.println("schema term::"+dbmd.getSchemaTerm());
			
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
