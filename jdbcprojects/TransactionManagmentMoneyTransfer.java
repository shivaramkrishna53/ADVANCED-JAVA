package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class TransactionManagmentMoneyTransfer {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int sender=0,reciver=0,amount=0;
		boolean flag=false;
		try
		{
			
			sc=new Scanner(System.in);
			System.out.println("Enter the sender account number::");
			sender=sc.nextInt();
			System.out.println("Enter the recivers account number::");
			reciver=sc.nextInt();
			System.out.println("Enter the amount to be transferred::");
			amount=sc.nextInt();
			//main principal behind transctionManagment is combining the related operations into one and executing them such that do everything or nothing principal
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			//disable auto-commit
			con.setAutoCommit(false);
			//create statement object
		    st=con.createStatement();
			st.addBatch("update bank_amounttransfer set balance=balance-"+amount+"where accno="+sender);
			st.addBatch("update bank_amounttransfer set balance=balance+"+amount+"where accno="+reciver);
			int result[]=st.executeBatch();
			for(int i=0;i<result.length;i++)
			{
				if(result[i]==0)
					flag=true;
			}
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
			if(flag)
			{
				con.rollback();
				System.out.println("rolled backed...transaction failed");
			}
			else
			{
				con.commit();
			System.out.println("commited succesfully...transaction succesfully completed");
			}
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}

	}

}
