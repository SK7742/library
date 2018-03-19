package com.Service;
import com.Constants.Message;
import com.Dao.*;
import com.utils.Data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.List;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class Welcome {

	private JFrame frmLogin;
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/images/book2.png")));
		frmLogin.setTitle("Login");
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 582, 407);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lbl = new JLabel("UserName :");
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl.setBounds(52, 137, 146, 39);
		frmLogin.getContentPane().add(lbl);
		
		JLabel lblPassword = new JLabel("PassWord :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(52, 183, 146, 39);
		frmLogin.getContentPane().add(lblPassword);
		
		String[] usertype= {"Student","Admin"};
		final JComboBox comboBox=new JComboBox(usertype);
		comboBox.setBounds(208, 241, 116, 27);
		frmLogin.getContentPane().add(comboBox);
		
		JLabel lblLoginAs = new JLabel("Login As :");
		lblLoginAs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginAs.setBounds(52, 233, 146, 39);
		frmLogin.getContentPane().add(lblLoginAs);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Welcome.class.getResource("/images/ok.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String selected_user_type=(String) comboBox.getItemAt(comboBox.getSelectedIndex());
			try
			{
				Data.main(null);
				Connection con=com.Dao.Connect.DBConnector();
				if(selected_user_type.equals("Student"))
				{
					int count=0;
					String student="select * from student where username=? and password=?";
					PreparedStatement pst=con.prepareStatement(student);
		            pst.setString(1, textField.getText() );
		            pst.setString(2, passwordField.getText());
		            ResultSet rs=pst.executeQuery();
		            while(rs.next())
		            {
		                count=count+1;
		                Data.name=rs.getString("name");
		            	Data.fname=rs.getString("fname");
		            	Data.id=rs.getString("id");
		            	Data.dob=rs.getString("dob");
		            	Data.address=rs.getString("address");
		            	Data.username=rs.getString("username");
		            	Data.password=rs.getString("password"); 
		            }
		            if(count==1)
		            {
		            	JOptionPane.showMessageDialog(null, "Welcome "+textField.getText());
		            	frmLogin.dispose();
		            	Home_Student.main(null); 	
		            }
		           
		            else
		            {
		                JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
		            }
		            pst.close();
				
				}
				else
				{
					int count=0;
					String admin="select name,password from admin where name=? and password=?";
					PreparedStatement pst=con.prepareStatement(admin);
		            pst.setString(1, textField.getText());
		            pst.setString(2, passwordField.getText());
		            ResultSet rs=pst.executeQuery();
		            while(rs.next())
		            {
		                count=count+1;
		            }
		            if(count==1)
		            {
		            	frmLogin.dispose();
		            	Home_Admin.main(null);
		            }
		           
		            else
		            {
		                JOptionPane.showMessageDialog(null, Message.LOGIN_ERROR);
		            }
		            pst.close();
				}
			}
			catch(Exception e)
			{
				
			}
		}
		});
		btnLogin.setBounds(334, 240, 126, 28);
		frmLogin.getContentPane().add(btnLogin);
		
		textField = new JTextField();
		textField.setBounds(208, 145, 252, 27);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 191, 252, 27);
		frmLogin.getContentPane().add(passwordField);
		
		JLabel lblWelcomeToLibrary = new JLabel("Welcome To Library Management System");
		lblWelcomeToLibrary.setForeground(new Color(255, 255, 0));
		lblWelcomeToLibrary.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		lblWelcomeToLibrary.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLibrary.setBounds(10, 11, 558, 57);
		frmLogin.getContentPane().add(lblWelcomeToLibrary);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Welcome.class.getResource("/images/cloud-1117279_1920.jpg")));
		label.setBounds(0, 0, 578, 381);
		frmLogin.getContentPane().add(label);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
