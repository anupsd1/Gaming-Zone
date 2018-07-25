import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.*;

public class Login implements ActionListener
{

	private JFrame frame;
	private JTextField username;
	private JPasswordField passwordField;
	ScreenManager s;
	private static final DisplayMode modes1[] =
		{
			new DisplayMode(800,600,32,0),
			new DisplayMode(800,600,24,0),
			new DisplayMode(800,600,16,0),
			new DisplayMode(640,480,32,0),
			new DisplayMode(640,480,24,0),
			new DisplayMode(640,480,16,0)
		};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Login();
		//window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Login() {
		//ScreenManager s=new ScreenManager();
		//initialize();
		/*frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); */


		//frame=(JFrame)s.getFullScreenWindow();
		s=new ScreenManager();
		DisplayMode dm=s.findFirstCompatibleMode(modes1);
		s.setFullScreen(dm);

		Window w=s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		frame=(JFrame)w;
		// Works- frame.setLayout(new FlowLayout());
		frame.setLayout(new BorderLayout());
		
		JPanel p1=new JPanel(new FlowLayout());
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(35, 76, 103, 52);
		//frame.getContentPane().add(lblUsername);
		p1.add(lblUsername);
		//w.add(lblUsername);

		/*JLabel label = new JLabel("");
		label.setBounds(43, 155, 46, 14);
		//frame.getContentPane().add(label);
		frame.add(label);
		//w.add(label); */
		
		username = new JTextField();
		username.setBounds(167, 94, 86, 20);
		//frame.getContentPane().add(username);
		p1.add(username);
		//w.add(username);
		username.setColumns(10);
		
		JPanel p3=new JPanel(new FlowLayout());
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		
		btnLogin.setBounds(93, 170, 89, 23);
		//frame.getContentPane().add(btnLogin);
		p3.add(btnLogin);
		//w.add(btnLogin);

		JPanel p2=new JPanel(new FlowLayout());
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(35, 129, 95, 30);
		//frame.getContentPane().add(lblPassword);
		p2.add(lblPassword);
		//w.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(169, 136, 84, 20);
		frame.getContentPane().add(passwordField);
		//w.add(passwordField);
		p2.add(passwordField);
		passwordField.setColumns(10);

		//frame.setVisible(true);
		//w.add(frame);
		frame.add(p1,BorderLayout.NORTH);
		frame.add(p2,BorderLayout.CENTER);
		frame.add(p3,BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent ae) {
				
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					Connection con=DriverManager.getConnection("jdbc:ucanaccess://C:/java//Game Development2//Database1.mdb");
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select * from Database");
					while (rs.next())
					{
						String s1=rs.getString(1);
						String s2=rs.getString(2);
						System.out.print(s1+"\t");
						System.out.println(s2);
						String uname=username.getText();
						String pad=passwordField.getText();
						if(s1.equals(uname) && (s2.equals(pad)))
						{
							try
							{
								Runtime.getRuntime().exec("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\mario6.exe", null, new File("C:\\Program Files\\java\\jdk1.8.0_131\\bin\\"));
							}
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
						}
					}
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				/* String uname=username.getText();
				String pad=passwordField.getText();
				
				
				if(uname.equals("name") && pad.equals("password"))
				{
					JOptionPane.showMessageDialog(frame, "You are successfully logged in");
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Invalid username");
				} */
			}
}
