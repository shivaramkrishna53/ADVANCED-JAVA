package jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class GuiFrontEndResultSetScrollable  extends JFrame implements ActionListener   {
private static final String Emp_Query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	
	  private JLabel lno,lname,ljob,lsal,lmsg;
	  private JTextField tno,tname,tjob,tsal;
	  private JButton bfirst,blast,bprev,bnext;
	  Connection con=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;
	
	 
	public GuiFrontEndResultSetScrollable()
	{
		System.out.println("GuiFrontEndResultSetScrollable-0 param constructor");
		setSize(300,300);
		setLayout(new FlowLayout());
		setTitle("GuiResulsetScrollable");
		setVisible(true);
		//add components
		
		 lno=new JLabel("Emp Number");
		add(lno);
		 tno=new JTextField(10);
		add(tno);
		 lname=new JLabel("Emp Name");
		add(lname);
		 tname=new JTextField(10);
		add(tname);
		 ljob=new JLabel("Emp Job");
		add(ljob);
		 tjob=new JTextField(10);
		add(tjob);
		 lsal=new JLabel("Emp salary");
		add(lsal);
		 tsal=new JTextField(10);
		add(tsal);
		//buttons
		 bfirst=new JButton("first");
		add(bfirst);
		 blast=new JButton("last");
		add(blast);
		 bprev=new JButton("previous");
		add(bprev);
		 bnext=new JButton("next");
		add(bnext);
		lmsg=new JLabel("");
		add(lmsg);
		bnext.addActionListener(this);
		bprev.addActionListener(this);
		blast.addActionListener(this);
		bfirst.addActionListener(this);
		jdbcInitialize();
	}
	
	public static void main(String[] args) {
		System.out.println("GuiFrontEndResultSetScrollable.main() method");
		new GuiFrontEndResultSetScrollable();
		

	}
	private void jdbcInitialize()
	{
		System.out.println("Jdbc initialization function");
		try
		{
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##shiva1234","shiva1234");
			ps=con.prepareStatement(Emp_Query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=ps.executeQuery();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		var flag=false;
		System.out.println("GuiFrontEndResultSetScrollable.actionPerformed()");
		lmsg.setText("");
		if(ae.getSource()==bfirst)
		{
			System.out.println("First Button clicked");
			try
			{
			rs.first();
			flag=true;
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		else
			if(ae.getSource()==blast)
			{
				System.out.println("Last Button clicked");
				try
				{
				rs.last();
				flag=true;
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
				if(ae.getSource()==bprev)
				{
					System.out.println("Previous Button Clicked");
					try
					{
						if(!rs.isFirst())
						{
							rs.previous();
							flag=true;
						}
						else
						{
							lmsg.setText("User is in first record, cannot move further to previous record");
							lmsg.setForeground(Color.RED);
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
					
					
				}
				else
				{
					System.out.println("Next Button Clicked");
					try
					{
					if(!rs.isLast())
					{
						rs.next();
						flag=true;
						
					}
					else
					{
						lmsg.setText("User is in last record, Cannot move to next record");
						lmsg.setForeground(Color.RED);
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
				}
		if(flag==true)
		{
			try
			{
			tno.setText(rs.getString(1));
			tname.setText(rs.getString(2));
			tjob.setText(rs.getString(3));
			tsal.setText(rs.getString(4));
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
