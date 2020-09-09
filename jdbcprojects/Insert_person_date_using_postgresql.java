package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Insert_person_date_using_postgresql {
private static final String person_date_details_insert_query="INSERT INTO PERSON_DETAILS VALUES(NEXTVAL('person_pid_seq'),?,?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declaration of local variables
		Scanner sc=null;
		String name=null,dob=null,doj=null,dom=null;
		PreparedStatement ps=null;
		Connection con=null;
		try
		{
			sc=new Scanner(System.in);
			//reading the inputs from the user
			if(sc!=null)
			{
				System.out.println("ENTER THE PERSON NAME::");
				name=sc.next();
				System.out.println("ENTER THE PERSON DOB(dd-MM-yyy)");
				dob=sc.next();
				System.out.println("ENTER THE PERSON DOJ(MM-dd-yyyy)");
				doj=sc.next();
				System.out.println("ENTER THE PERSON DOM(yyyy-MM-dd)");
				dom=sc.next();
			}
			//converting the string to sql dates
			//dob
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date utldob=sdf.parse(dob);
			java.sql.Date sqldob=new java.sql.Date(utldob.getTime());
			
			//doj
			SimpleDateFormat sdfe=new SimpleDateFormat("MM-dd-yyyy");
			java.util.Date utldoj=sdfe.parse(doj);
			java.sql.Date sqldoj=new java.sql.Date(utldoj.getTime());
			
			//dom
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utldom=sdf2.parse(dom);
			java.sql.Date sqldom=new java.sql.Date(utldom.getTime());
			
			//get jdbc connection
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/advjavadb","postgres","shiva1234");
			//getting preparedstatement object
			if(con!=null)
			 ps=con.prepareStatement(person_date_details_insert_query);
			//setting the input params for the query
			if(ps!=null)
			{
				ps.setString(1,name);
				ps.setDate(2,sqldob);
				ps.setDate(3,sqldoj);
				ps.setDate(4,sqldom);
				//executing the sql query
				int count=ps.executeUpdate();
				if(count==1)
					System.out.println("Record Successfully inserted");
				else
					System.out.println("Failed to insert");
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
