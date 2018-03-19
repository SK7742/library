package com.Service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Dao.Connect;
import com.utils.Data;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Book_deposit extends JDialog {
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text6;
	private JTextField text4;
	private JTextField text5;
	private JTable table;
	private JTable table_1;
	private JTextField text7;
	private JTextField text9;
	private JTextField text8;
	private JTextField text10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Book_deposit dialog = new Book_deposit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	static Connection con=Connect.DBConnector();
	static String count="";
	private JTextField text11;
	public Book_deposit() {
		setTitle("Book Deposit");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Book_deposit.class.getResource("/images/book2.png")));
		setBounds(100, 100, 630, 620);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 614, 582);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Student Id :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 11, 89, 25);
		panel.add(label);
		
		text1 = new JTextField();
		text1.setColumns(10);
		text1.setBounds(106, 11, 125, 20);
		panel.add(text1);
		
		JLabel label_1 = new JLabel("Name :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(255, 11, 89, 25);
		panel.add(label_1);
		
		text2 = new JTextField();
		text2.setEditable(false);
		text2.setColumns(10);
		text2.setBounds(351, 11, 253, 20);
		panel.add(text2);
		
		JLabel label_2 = new JLabel("Father's Name:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(255, 42, 89, 25);
		panel.add(label_2);
		
		text3 = new JTextField();
		text3.setEditable(false);
		text3.setColumns(10);
		text3.setBounds(351, 42, 253, 20);
		panel.add(text3);
		
		JLabel label_3 = new JLabel("Address :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(255, 78, 89, 25);
		panel.add(label_3);
		
		text6 = new JTextField();
		text6.setEditable(false);
		text6.setColumns(10);
		text6.setBounds(351, 78, 253, 20);
		panel.add(text6);
		
		JLabel label_4 = new JLabel("Class :");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(10, 42, 89, 25);
		panel.add(label_4);
		
		text4 = new JTextField();
		text4.setEditable(false);
		text4.setColumns(10);
		text4.setBounds(106, 42, 125, 20);
		panel.add(text4);
		
		JLabel label_5 = new JLabel("DOB :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 78, 89, 25);
		panel.add(label_5);
		
		text5 = new JTextField();
		text5.setEditable(false);
		text5.setColumns(10);
		text5.setBounds(106, 78, 125, 20);
		panel.add(text5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(106, 137, 49, 14);
		panel.add(label_6);
		
		JButton button = new JButton("Get Record");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt=null;
				if(!text1.getText().equals(""))
				{	
					try
					{
						Data.id=text1.getText();
						String query="select *from student where id='"+Data.id+"'";
						stmt=con.createStatement();
						ResultSet rs=stmt.executeQuery(query);
						while(rs.next())
						{
							Data.name=rs.getString("name");
							Data.fname=rs.getString("fname");
							Data.dob=rs.getString("dob");
							Data.address=rs.getString("address");
							text4.setText(rs.getString("class"));	
						}
						text2.setText(Data.name);
						text3.setText(Data.fname);
						text5.setText(Data.dob);
						text6.setText(Data.address);
						String query2="select *from current_issued where student_id='"+Data.id+"'";
						Statement stmt2=con.createStatement();
						ResultSet rs2=stmt2.executeQuery(query2);
						table.setModel(DbUtils.resultSetToTableModel(rs2));
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Enter Student Id");
				}
		});
		button.setBounds(501, 268, 103, 23);
		panel.add(button);
		
		table = new JTable();
		table.setBounds(10, 162, 594, 95);
		panel.add(table);
		
		JLabel label_7 = new JLabel("Books Allotted ");
		label_7.setBounds(243, 126, 89, 25);
		panel.add(label_7);
		
		table_1 = new JTable();
		table_1.setBounds(10, 346, 594, 124);
		panel.add(table_1);
		
		JLabel label_8 = new JLabel("Search Result :");
		label_8.setBounds(10, 321, 89, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Book Id :");
		label_9.setBounds(10, 484, 66, 14);
		panel.add(label_9);
		
		text7 = new JTextField();
		text7.setColumns(10);
		text7.setBounds(59, 481, 96, 20);
		panel.add(text7);
		
		JButton button_2 = new JButton("Fetch");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(text1.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Enter Student Id");
				else if(text7.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Enter Book Id");
				else
				{
				try
				{
					Statement stmt5=null;
					Data.book_id=text7.getText();
					String query5="select *from current_issued where book_id='"+Data.book_id+"'";
					
					stmt5=con.createStatement();
					ResultSet rs5=stmt5.executeQuery(query5);
					while(rs5.next())
					{
						Data.book_name=rs5.getString("book_name");
						Data.date_issued=rs5.getString("date_issued");
						Data.author=rs5.getString("book_author");
						Data.date_deposite=rs5.getString("date_deposit");
						
					}
					text8.setText(Data.book_name);
					text9.setText(Data.date_issued);
					text10.setText(Data.author);
					text11.setText(Data.date_deposite);
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
			}
		});
		button_2.setBounds(165, 481, 89, 23);
		panel.add(button_2);
		
		JLabel lblDepositeOn = new JLabel("Issued Date :");
		lblDepositeOn.setBounds(10, 512, 76, 14);
		panel.add(lblDepositeOn);
		
		text9 = new JTextField();
		text9.setEditable(false);
		text9.setColumns(10);
		text9.setBounds(96, 509, 158, 20);
		panel.add(text9);
		
		JLabel label_11 = new JLabel("Book Name :");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(295, 484, 66, 14);
		panel.add(label_11);
		
		text8 = new JTextField();
		text8.setEditable(false);
		text8.setColumns(10);
		text8.setBounds(373, 481, 231, 20);
		panel.add(text8);
		
		JLabel label_12 = new JLabel("Author :");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		label_12.setBounds(295, 515, 66, 14);
		panel.add(label_12);
		
		text10 = new JTextField();
		text10.setEditable(false);
		text10.setColumns(10);
		text10.setBounds(373, 512, 231, 20);
		panel.add(text10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(text1.getText().equals("")||text7.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Please Enter Above Details");
				else
				{
					Statement stmt=null,stmt2=null,stmt3=null,stmt4=null;
					try
					{	
						int update=0;
						String query3="delete from current_issued where student_id='"+Data.id+"' and book_id='"+Data.book_id+"'";
						stmt3=con.createStatement();
						update=stmt3.executeUpdate(query3);
						if(update!=0)
						{
							try
							{
								Statement stmt6=null,stmt7=null;
								String query="select *from books where book_id='"+Data.book_id+"'";
								stmt6=con.createStatement();
								ResultSet rs6=stmt6.executeQuery(query);
								while(rs6.next())
								Data.available=rs6.getString("available");	
								int temp=Integer.parseInt(Data.available)+1;
								String query7="update books set available='"+temp+"' where book_id='"+Data.book_id+"'";
								stmt7=con.createStatement();
								int result=stmt7.executeUpdate(query7);
							}
							catch(Exception e)
							{
								JOptionPane.showMessageDialog(null, e);
							}
							JOptionPane.showMessageDialog(null, "Book Deposited SuccessFully");
							settext();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error");
						}
						
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
				}
				
			}
		});
		btnDeposit.setBounds(515, 543, 89, 23);
		panel.add(btnDeposit);
		
		JLabel lblDepositeDate = new JLabel("Deposite Date :");
		lblDepositeDate.setBounds(10, 540, 76, 14);
		panel.add(lblDepositeDate);
		
		text11 = new JTextField();
		text11.setEditable(false);
		text11.setColumns(10);
		text11.setBounds(96, 537, 158, 20);
		panel.add(text11);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(Book_deposit.class.getResource("/images/mountains-3236276.jpg")));
		label_10.setBounds(0, 0, 614, 582);
		panel.add(label_10);
	}
	void settext()
	{
		text1.setText("");
		text2.setText("");
		text3.setText("");
		text4.setText("");
		text5.setText("");
		text6.setText("");
		text7.setText("");
		text8.setText("");
		text9.setText("");
		text10.setText("");
		text11.setText("");
	}
}
