package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



/*CREATE DEFINER=`root`@`localhost` PROCEDURE `customer_details`(in cnum int,out name varchar(20),out addrs varchar(20),out bill float)
BEGIN
select cname,cadd,cbill into name,addrs,bill where cno=cnum;
END
/*/


public class CsProcedureMysqlCustomerResultSetTest10 {
private static final String customer_Query="{call p_cus_details(?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		int id=0;
		String custname=null,custaddrs=null;
		float custbill=0.0f;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;

		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				
				System.out.println("Enter the customer id:");
				id=sc.nextInt();
				//establish the connection
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advjavadb","root","shiva1234");
				//get callable statement object
				if(con!=null)
				cs=con.prepareCall(customer_Query);
				if(cs!=null)
				{
			
				//set the input params
				cs.setInt(1,id);
				//executing the query
				cs.execute();
				rs=cs.getResultSet();
				if(rs!=null)
				{
					if(rs.next())
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					else
						System.out.println("NO records found with that id");
				}
				}
			}
		}
		catch(SQLException se)
		{
			if(se.getErrorCode()==1403)
				System.out.println("No Emp with such details");
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
			if(cs!=null)
				cs.close();
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
			try
			{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}

}
