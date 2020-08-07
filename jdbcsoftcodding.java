import java.sql.*;
import java.util.Scanner;
class jdbcsoftcodding 
{
	public static void main(String[] args) throws Exception
	{
		boolean flag=false;
		Scanner sc=new Scanner(System.in);


		/*int lowersal,uppersal;
		System.out.println("Enter the lower sal:");
		lowersal=sc.nextInt();
		System.out.println("Enter the upper sal:");
		uppersal=sc.nextInt();
		*/ 


		/*System.out.println("Enter the initial charcters of the employee name that to be found");
		String inicha=sc.next();
		String finalch="'"+inicha+"%"+"'";
		*/



        System.out.print("Enter the first job");
		String firjob=sc.next();
		System.out.println("Enter the second job");
		String secjob=sc.next();
		System.out.println("Enter the third job");
		String thirjob=sc.next();
		String finaljob="('"+firjob+"','"+secjob+"','"+thirjob+"')";

		//String query="select empno,ename,job,sal from emp where sal>="+lowersal+" and sal<="+uppersal;
		//String query="select * from emp where ename like "+finalch;
		String query="select * from emp where job in"+finaljob;
		System.out.println(query);
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()==true)
		{
			flag=true;
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
		}
		System.out.println(flag?"records found":"records not found");
		rs.close();
		st.close();
		con.close();


	}
}
