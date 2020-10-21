package jdbc;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GuiDevelopmentUsingGuiBuilderPlugin extends JFrame {
	private static final String Emp_Query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	private JPanel contentPane;
	private JTextField tname;
	private JTextField tjob;
	private JTextField tsal;
	private JTextField tno;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiDevelopmentUsingGuiBuilderPlugin frame = new GuiDevelopmentUsingGuiBuilderPlugin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	//jdbc initializing logic
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

	/**
	 * Create the frame.
	 */
	public GuiDevelopmentUsingGuiBuilderPlugin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Number");
		lblNewLabel.setBounds(23, 26, 120, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee  Name");
		lblNewLabel_1.setBounds(23, 102, 108, 23);
		contentPane.add(lblNewLabel_1);
		
		tname = new JTextField();
		tname.setBounds(251, 104, 96, 19);
		contentPane.add(tname);
		tname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Employee Job");
		lblNewLabel_2.setBounds(35, 216, 96, 23);
		contentPane.add(lblNewLabel_2);
		
		tjob = new JTextField();
		tjob.setBounds(251, 218, 96, 19);
		contentPane.add(tjob);
		tjob.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Employee Salary");
		lblNewLabel_3.setBounds(31, 352, 100, 13);
		contentPane.add(lblNewLabel_3);
		
		tsal = new JTextField();
		tsal.setBounds(251, 335, 96, 19);
		contentPane.add(tsal);
		tsal.setColumns(10);
		
		JLabel lmsg=new JLabel("");
		lmsg.setBounds(60,452, 500, 63);
		contentPane.add(lmsg);
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(!rs.isLast())
					{
						
						rs.next();
						tno.setText(rs.getString(1));
						tname.setText(rs.getString(2));
						tjob.setText(rs.getString(3));
						tsal.setText(rs.getString(4));
					}
					else
					{
						lmsg.setText("You are at last record, cannot move to next records further");
						lmsg.setForeground(Color.RED);
					}
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception we)
				{
					we.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(49, 422, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("PREVIOUS");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(!rs.isFirst())
					{
						lmsg.setText("");
						rs.previous();
						tno.setText(rs.getString(1));
						tname.setText(rs.getString(2));
						tjob.setText(rs.getString(3));
						tsal.setText(rs.getString(4));
					}
					else
					{
						lmsg.setText("You are at first record, cannot move to previous records further");
						lmsg.setForeground(Color.RED);
					}
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception we)
				{
					we.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(214, 422, 120, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("FIRST");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lmsg.setText("");
				try
				{
				rs.first();
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tjob.setText(rs.getString(3));
				tsal.setText(rs.getString(4));
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception we)
				{
					we.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(411, 422, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LAST");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lmsg.setText("");
				try
				{
				rs.last();
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tjob.setText(rs.getString(3));
				tsal.setText(rs.getString(4));
				}
				catch(SQLException se)
				{
					se.printStackTrace();
				}
				catch(Exception we)
				{
					we.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setBounds(647, 422, 85, 21);
		contentPane.add(btnNewButton_3);
		
		tno = new JTextField();
		tno.setBounds(251, 28, 96, 19);
		contentPane.add(tno);
		tno.setColumns(10);
		
		jdbcInitialize();
	}
}
