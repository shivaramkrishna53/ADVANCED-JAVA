import java.sql.*;
class resultsettest 
{
	public static void main(String[] args) throws Exception
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","shiva","shiva1234");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from emp");
		while(rs.next()==true)
		{
			//System.out.println(rs.getInt("empid")+"  "+rs.getString("empname")+"  "+rs.getFloat("empsal"));
			// System.out.println(rs.getString("empid")+"  "+rs.getString("empname")+"  "+rs.getString("empsal"));
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
		}
		rs.close();
		st.close();
		con.close();
		




	}
}
