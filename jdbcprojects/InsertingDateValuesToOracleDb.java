package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertingDateValuesToOracleDb {
private static final String Insert_date_values_query="INSERT INTO PERSON_DATE_DETAILS VALUES(PERSON_DATE_DETAILS_SEQUENCE.NEXTVAL,?,?,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaring local variables
		Scanner sc=null;
		int count=0;
		String name=null,address=null,dob=null,doj=null,dom=null;
		SimpleDateFormat sdfdob=null,sdfdoj=null,sdfdom=null;
		Connection con=null;
		PreparedStatement ps=null;
		
		
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter person name::");
				name=sc.next();
				System.out.println("Enter person address::");
				address=sc.next();
				System.out.println("Enter person dob(dd-MM-yyyy)::");
				dob=sc.next();
				System.out.println("Enter person doj(MM-dd-yyyy)::");
				doj=sc.next();
				System.out.println("Enter person dom(yyyy-MM-dd::");
				dom=sc.next();
				//converting the given string to respective Date object format
				//dob
				sdfdob=new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date utdob=sdfdob.parse(dob);
				java.sql.Date sqdob=new java.sql.Date(utdob.getTime());
				//doj
				 sdfdoj=new SimpleDateFormat("MM-dd-yyyy");
				 java.util.Date utdoj=sdfdoj.parse(doj);
				 java.sql.Date sqdoj=new java.sql.Date(utdoj.getTime());
				 //dom
				  sdfdom=new SimpleDateFormat("yyyy-MM-dd");
				 java.util.Date utdom=sdfdom.parse(dom);
				 java.sql.Date sqdom=new java.sql.Date(utdom.getTime());
				 
				 //Registering oracle jdbc driver
				 Class.forName("oracle.jdbc.driver.OracleDriver");
				 
				 //get connection jdbc object
				 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234", "shiva1234");
				 //creating jdbc prepared statemetn object
				 if(con!=null)
					 ps=con.prepareStatement(Insert_date_values_query);
				 //insert values to prepared statement query 
				 ps.setString(1,name);
				 ps.setString(2,address);
				 ps.setDate(3,sqdob);
				 ps.setDate(4,sqdoj);
				 ps.setDate(5,sqdom);
				count= ps.executeUpdate();
				if(count==0)
					System.out.println("Failed to insert person details");
				else
					System.out.println("Successfully inserted");
				 
				 
				
				
				
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
		}//finally
		
		
		

	}

}
