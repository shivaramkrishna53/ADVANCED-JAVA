package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ExcelInsertTest {
private static final String query="insert into collegedataexcel.sheet1 values(?,?,?)";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		String name=null,roll=null,add=null;
		try
		{
			sc=new Scanner(System.in);
			System.out.println("enter the rollno  of the student::");
			roll=sc.next();
			System.out.println("enter the name  of the student::");
			name=sc.next();
			System.out.println("enter the address  of the student::");
			add=sc.next();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:Excel:///E:\\shiva java developer full stack"))
		{
			try(PreparedStatement ps=con.prepareStatement(query))
			{
				ps.setString(1,roll);
				ps.setString(2,name);
				ps.setString(3,add);
				int count=ps.executeUpdate();
				if(count==0)
					System.out.println("Failed to insert into excel sheet");
				else
					System.out.println("successully inserted in excel sheet");
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
