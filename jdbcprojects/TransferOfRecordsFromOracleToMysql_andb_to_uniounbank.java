package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransferOfRecordsFromOracleToMysql_andb_to_uniounbank {
private static final String ORACLE_ANDRABANK_RECORDSFETCHING_QUERY="SELECT ACCNO,ACCHOLDERNAME,BALANCE FROM ANDRABANK";
private static final String MYSQL_UNIONBANK_RECORDSSTORING_FROM_ANDBORACLE_QUERY="INSERT INTO UNION_BANK(ACCNO,ACCHOLDERNAME,BALANCE) VALUES(?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variable declaration
		Connection oraclecon=null,mysqlcon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int accno=0;
		String accholdername=null;
		float balance=0.0f;
		try
		{
			//registering  of the jdbc driver's(optional)
			//oracle driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//mysql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//creating connection jdbc objects
			oraclecon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234", "shiva1234");
			mysqlcon=DriverManager.getConnection("jdbc:mysql:///advjavadb","root","shiva1234");
			//creating statement jdbc objects
			st=oraclecon.createStatement(); //taking
			rs=st.executeQuery(ORACLE_ANDRABANK_RECORDSFETCHING_QUERY);
			ps=mysqlcon.prepareStatement(MYSQL_UNIONBANK_RECORDSSTORING_FROM_ANDBORACLE_QUERY);
			if(rs!=null && ps!=null)
			{
				while(rs.next())
				{
					//getting records from rs(andra bank)
					accno=rs.getInt(1);
					accholdername=rs.getString(2);
					balance=rs.getFloat(3);
					
					
					//putting records to ps(union bank)
					ps.setInt(1,accno);
					ps.setString(2,accholdername);
					ps.setFloat(3,balance);
					ps.executeUpdate();
					
					
					
				}
				System.out.println("Successfully all Account details are copied from Andrabank(oracle db) to Unionbank(mysql db)");
			}
			//change the values in oracle db and run the program otherwise it leads to duplicate values of accno
			
		
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
				if(ps!=null)
					ps.close();
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
				if(mysqlcon!=null)
					mysqlcon.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(oraclecon!=null)
					oraclecon.close();
			}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
			}//finally
		}//main

	}//class


