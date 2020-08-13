import java.sql.*;


class connectionTest 
{
	
	public static void main(String[] args) throws Exception
	{
		oracle.jdbc.driver.OracleDriver obj=new oracle.jdbc.driver.OracleDriver();  //activate jdbc driver s/w by loading the driverclass
		DriverManager.registerDriver(obj);   //register jdbc driver object with jdbc divermanager
	
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","shiva","shiva1234");    //DriverManager.getConnection(url,username,password);
        if(con==null)
			System.out.println("connection failed to established");
		else
			System.out.println("Connection established successfully ");
	}
}
