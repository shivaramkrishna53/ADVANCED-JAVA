import java.sql.*;
class  statementresult
{
	public static void main(String[] args) throws Exception
	{
		Connection con=Drivermanager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","shiva","shiva1234");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from emp");
		int count=st.executeUpdate("insert into emp values(101,shiva,50000)");
		System.out.println(""+count+"number of records inserted into database");


		
	}
}
