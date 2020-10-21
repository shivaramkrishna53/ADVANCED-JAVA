package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



/*CREATE DEFINER=`root`@`localhost` PROCEDURE `customer_details`(in cnum int,out name varchar(20),out addrs varchar(20),out bill float)
BEGIN
select cname,cadd,cbill into name,addrs,bill where cno=cnum;
END
/*/


public class CsProcedureMysqlCustomerTest8 {
private static final String customer_Query="{call customer_details(?,?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//local variables
		Scanner sc=null;
		int id=0;
		String custname=null,custaddrs=null;
		float custbill=0.0f;
		Connection con=null;
		CallableStatement cs=null;

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
				//registering the out params
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.FLOAT);
				//set the input params
				cs.setInt(1,id);
				//executing the query
				cs.execute();
				custname=cs.getString(2);
				custaddrs=cs.getString(3);
				custbill=cs.getFloat(4);
				System.out.println(custname+ " "+ custaddrs+"  "+custbill);
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
