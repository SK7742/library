package com.Service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Dao.Connect;
import com.utils.Data;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Book_issue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text6;
	private JTextField text4;
	private JTextField text5;
	private JTable table;
	private static JTable table2;
	private JTextField text7;
	private JTextField text9;
	private JTextField text8;
	private JTextField text10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Book_issue dialog = new Book_issue();
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
	
	public Book_issue() {
		setTitle("Book Issue");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Book_issue.class.getResource("/images/book2.png")));
		setBounds(100, 100, 630, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEnterStudentId = new JLabel("Student Id :");
			lblEnterStudentId.setBounds(10, 11, 89, 25);
			lblEnterStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblEnterStudentId);
		}
		{
			text1 = new JTextField();
			text1.setBounds(106, 11, 125, 20);
			contentPanel.add(text1);
			text1.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name :");
			lblName.setBounds(255, 11, 89, 25);
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblName);
		}
		{
			text2 = new JTextField();
			text2.setBounds(351, 11, 253, 20);
			text2.setEditable(false);
			text2.setColumns(10);
			contentPanel.add(text2);
		}
		{
			JLabel lblFathersName = new JLabel("Father's Name:");
			lblFathersName.setBounds(255, 42, 89, 25);
			lblFathersName.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblFathersName);
		}
		{
			text3 = new JTextField();
			text3.setBounds(351, 42, 253, 20);
			text3.setEditable(false);
			text3.setColumns(10);
			contentPanel.add(text3);
		}
		{
			JLabel lblAddress = new JLabel("Address :");
			lblAddress.setBounds(255, 78, 89, 25);
			lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblAddress);
		}
		{
			text6 = new JTextField();
			text6.setBounds(351, 78, 253, 20);
			text6.setEditable(false);
			text6.setColumns(10);
			contentPanel.add(text6);
		}
		{
			JLabel lblClass = new JLabel("Class :");
			lblClass.setBounds(10, 42, 89, 25);
			lblClass.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblClass);
		}
		{
			text4 = new JTextField();
			text4.setBounds(106, 42, 125, 20);
			text4.setEditable(false);
			text4.setColumns(10);
			contentPanel.add(text4);
		}
		{
			JLabel lblDob = new JLabel("DOB :");
			lblDob.setBounds(10, 78, 89, 25);
			lblDob.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPanel.add(lblDob);
		}
		{
			text5 = new JTextField();
			text5.setBounds(106, 78, 125, 20);
			text5.setEditable(false);
			text5.setColumns(10);
			contentPanel.add(text5);
		}
		{
			JLabel label = new JLabel("");
			label.setBounds(106, 137, 49, 14);
			contentPanel.add(label);
		}
		{
			JButton btnGetRecord = new JButton("Get Record");
			btnGetRecord.addActionListener(new ActionListener() {
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
						
						String query5="select count(*) from current_issued where student_id='"+Data.id+"'";
						PreparedStatement pst=con.prepareStatement(query5);
						ResultSet rs5=pst.executeQuery();
						while(rs5.next())
						{
							count=rs5.getString("count(*)");
						}
						int count2=Integer.parseInt(count);
						if(count2>2)
						{
							JOptionPane.showMessageDialog(null, "Maximum 3 Books are allowed per Student");
							settext();
						}	
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
			btnGetRecord.setBounds(351, 268, 103, 23);
			contentPanel.add(btnGetRecord);
		}
		
		table = new JTable();
		table.setBounds(10, 162, 594, 95);
		contentPanel.add(table);
		
		JLabel lblBooksAl = new JLabel("Books Allotted ");
		lblBooksAl.setBounds(243, 126, 89, 25);
		contentPanel.add(lblBooksAl);
		
		table2 = new JTable();
		table2.setBounds(10, 346, 594, 124);
		contentPanel.add(table2);
		
		JLabel lblSearchResult = new JLabel("Search Result :");
		lblSearchResult.setBounds(10, 321, 89, 14);
		contentPanel.add(lblSearchResult);
		
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Data.select="book_fetch";
				Search.main(null);
			}
		});
		btnSearchBook.setBounds(464, 268, 136, 23);
		contentPanel.add(btnSearchBook);
		
		JLabel lblBookId = new JLabel("Book Id :");
		lblBookId.setBounds(10, 484, 66, 14);
		contentPanel.add(lblBookId);
		
		text7 = new JTextField();
		text7.setBounds(59, 481, 96, 20);
		contentPanel.add(text7);
		text7.setColumns(10);
		
		JButton btnFetch = new JButton("Fetch");
		btnFetch.addActionListener(new ActionListener() {
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
					String query5="select *from books where book_id='"+Data.book_id+"'";
					
					stmt5=con.createStatement();
					ResultSet rs5=stmt5.executeQuery(query5);
					while(rs5.next())
					{
						Data.book_name=rs5.getString("book_name");
						Data.price=rs5.getString("book_name");
						Data.author=rs5.getString("author");
						Data.available=rs5.getString("available");
					}
					text8.setText(Data.book_name);
					text9.setText(Data.price);
					text10.setText(Data.author);
					text11.setText(Data.available);	
					if(Data.available.equals("0"))
					{
						JOptionPane.showMessageDialog(null, "Currently Book Not Available");
						Data.book_id="";
						text7.setText("");
						text8.setText("");
						text9.setText("");
						text10.setText("");
						text11.setText("");	
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				}
			}
		});
		btnFetch.setBounds(165, 481, 89, 23);
		contentPanel.add(btnFetch);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(10, 512, 49, 14);
		contentPanel.add(lblPrice);
		
		text9 = new JTextField();
		text9.setEditable(false);
		text9.setBounds(59, 509, 195, 20);
		text9.setColumns(10);
		contentPanel.add(text9);
		{
			JLabel lblBookName = new JLabel("Book Name :");
			lblBookName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblBookName.setBounds(295, 484, 66, 14);
			contentPanel.add(lblBookName);
		}
		{
			text8 = new JTextField();
			text8.setEditable(false);
			text8.setColumns(10);
			text8.setBounds(373, 481, 231, 20);
			contentPanel.add(text8);
		}
		{
			JLabel lblAuthor = new JLabel("Author :");
			lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAuthor.setBounds(295, 515, 66, 14);
			contentPanel.add(lblAuthor);
		}
		{
			text10 = new JTextField();
			text10.setEditable(false);
			text10.setColumns(10);
			text10.setBounds(373, 512, 231, 20);
			contentPanel.add(text10);
		}
		{
			JButton btnAllot = new JButton("Allot");
			btnAllot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text1.getText().equals("")||text7.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Please Enter Above Details");
					else
					{
						SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
						Calendar c = Calendar.getInstance();
						Data.date_issued = dateformat.format(c.getTime());
						c.add(Calendar.DATE, 25); // Adding 25 days
						Data.date_deposite=dateformat.format(c.getTime());
						
						Statement stmt=null,stmt2=null,stmt3=null,stmt4=null;
						try
						{	
							int update=0;
							String query3="insert into current_issued values ('"+Data.book_id+"','"+Data.book_name+"','"+Data.author+"','"+Data.name+"','"+Data.id+"','"+Data.date_issued+"','"+Data.date_deposite+"')";
							stmt3=con.createStatement();
							update=stmt3.executeUpdate(query3);
							if(update!=0)
							{
								try
								{
									Statement stmt6=null;
									int temp=Integer.parseInt(Data.available)-1;
									String query6="update books set available='"+temp+"' where book_id='"+Data.book_id+"'";
									stmt=con.createStatement();
									int result=stmt.executeUpdate(query6);
								}
								catch(Exception e)
								{
									JOptionPane.showMessageDialog(null, e);
								}
								JOptionPane.showMessageDialog(null, "Book Issued SuccessFully");
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
			btnAllot.setBounds(515, 543, 89, 23);
			contentPanel.add(btnAllot);
		}
		{
			JLabel lblAvailable = new JLabel("Available :");
			lblAvailable.setBounds(10, 540, 66, 14);
			contentPanel.add(lblAvailable);
		}
		{
			text11 = new JTextField();
			text11.setEditable(false);
			text11.setColumns(10);
			text11.setBounds(86, 540, 168, 20);
			contentPanel.add(text11);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Book_issue.class.getResource("/images/mountains-3236276.jpg")));
			lblNewLabel.setBounds(0, 0, 614, 582);
			contentPanel.add(lblNewLabel);
		}
	}

	public static void result()
	{
		try
		{
			Statement stmt3=null;
			ResultSet rs3=null;
			String query3="select *from books where book_name like '%"+Data.book_search+"%' or author like '%"+Data.book_search+"%'";
			stmt3=con.createStatement();
			rs3=stmt3.executeQuery(query3);
			if(rs3==null)
				JOptionPane.showMessageDialog(null, "Book Not Found");
			else
				table2.setModel(DbUtils.resultSetToTableModel(rs3));	
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
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
