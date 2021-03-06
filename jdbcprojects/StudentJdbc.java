package com.nt.studentdetails;
import java.util.Scanner; //explicit import
import java.lang.Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
//creating StudentJdbc class to get details from database
/* documentation comments
*version:3.0
*author:oracle team
*/
class StudentJdbc 
{
	public static void main(String[] args) 
	{
		String city1=null,city2=null,city3=null,subquery=null,query=null;
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try //try 
		{
		
		//sql qurery to fetch::select sid,sname,savg,scityname from student where scityname in('hyd','chennai','delhi') order by scityname;
		//holding the citynames dynamically
		sc=new Scanner(System.in);
		System.out.println("Enter the city type1:::");
		if(sc!=null)
			{
			city1=sc.next().toLowerCase();
			System.out.println("Enter the city type2::");
			city2=sc.next().toLowerCase();
			System.out.println("Enter the city type3::");
			city3=sc.next().toLowerCase();
			}
			//framing the a String to hold the subquery
			subquery="('"+city1+"','"+city2+"','"+city3+"')"+"order by scityname,sname";
			query="select sid,sname,savg,scityname from student where scityname in"+subquery;
			System.out.println(query);
			//class.forName('oracle.jdbc.driver.OracleDriver');
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");   //creating connection object
			if(con!=null)
				st=con.createStatement();   //creating statement object
			if(st!=null)
				rs=st.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3)+"  "+rs.getString(4));
				}
			}
				System.out.println(flag?"Records found sucessfully":"No Records Found");

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
				if(rs!=null)
					rs.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(st!=null)
					st.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(con!=null)
					con.close();
			}
			catch (SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(sc!=null)
					sc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}


		
	}
}
