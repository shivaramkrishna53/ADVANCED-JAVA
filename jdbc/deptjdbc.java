import java.sql.*;
class deptjdbc 
{
	public static void main(String[] args) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from dept");
		while(rs.next()==true)
		{
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		}
		rs.close();
		st.close();
		con.close();
	}
}
