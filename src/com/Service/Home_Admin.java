package com.Service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.Dao.Connect;
import com.utils.Add_book;
import com.utils.Add_student;
import com.utils.Data;
import com.utils.Modify_book;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Home_Admin {

	private JFrame frmAdmin;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_Admin window = new Home_Admin();
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static Connection con=Connect.DBConnector();

	/**
	 * Create the application.
	 */
	public Home_Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setTitle("Admin");
		frmAdmin.setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Admin.class.getResource("/images/book2.png")));
		frmAdmin.setResizable(false);
		frmAdmin.setBounds(100, 100, 1064, 480);
		frmAdmin.setLocationRelativeTo(null);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frmAdmin.setJMenuBar(menuBar);
		
		JMenu mnNew = new JMenu("New");
		menuBar.add(mnNew);
		
		JMenuItem mntmAddBook = new JMenuItem("Add Book");
		mntmAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_book.main(null);
			}
		});
		mnNew.add(mntmAddBook);
		
		JMenuItem mntmNewStudent = new JMenuItem("New Student");
		mntmNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Add_student.main(null);
			}
		});
		mnNew.add(mntmNewStudent);
		
		JMenu mnBook = new JMenu("Book");
		menuBar.add(mnBook);
		
		JMenuItem mntmBookIssue = new JMenuItem("Book Issue");
		mntmBookIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Book_issue.main(null);
			}
		});
		mnBook.add(mntmBookIssue);
		
		JMenuItem mntmBookDeposit = new JMenuItem("Book Deposit");
		mntmBookDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Book_deposit.main(null); 
			}
		});
		mnBook.add(mntmBookDeposit);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem menuItem = new JMenuItem("Search Book");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Data.select="admin_search";
				Search.main(null);
			}
		});
		mnSearch.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Search Student");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Data.select="admin_student";
				Search.main(null);
			}
		});
		mnSearch.add(menuItem_1);
		
		JMenu mnModify = new JMenu("Modify");
		menuBar.add(mnModify);
		
		JMenuItem menuItem_2 = new JMenuItem("Modify Book");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Modify_book.main(null);
			}
		});
		mnModify.add(menuItem_2);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem menuItem_3 = new JMenuItem("Book List");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query="select *from books";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		mnView.add(menuItem_3);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAdmin.dispose();
				Welcome.main(null);
			}
		});
		mnUser.add(mntmLogout);
		frmAdmin.getContentPane().setLayout(null);
		
		JLabel label2 = new JLabel("Recent Issued Books");
		label2.setFont(new Font("Tahoma", Font.BOLD, 20));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setIcon(null);
		label2.setBounds(10, 0, 1026, 79);
		frmAdmin.getContentPane().add(label2);
		
		ResultSet rs = null;
		try
		{
			Connection con=com.Dao.Connect.DBConnector();
			String query="select *from current_issued";
			PreparedStatement pst=con.prepareStatement(query);
			 rs=pst.executeQuery();		
		}
		catch(Exception e)
		{
			
		}
		table = new JTable();
		table.setBounds(10, 91, 1026, 323);
		frmAdmin.getContentPane().add(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Home_Admin.class.getResource("/images/mountains-3236276.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1050, 429);
		frmAdmin.getContentPane().add(lblNewLabel_1);
		
	}
	public static void result()
	{
		try
		{
			Statement stmt=null;
			String query="select *from books where book_name like '%"+Data.book_search+"%' or author like '%"+Data.book_search+"%'";
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	public static void search_student()
	{
		try
		{
			Statement stmt=null;
			String query="select *from student where name like '%"+Data.student_search+"%' or fname like '%"+Data.student_search+"%' or class like '%"+Data.student_search+"%' or address like '%"+Data.student_search+"%' or username like '%"+Data.student_search+"%'";
			stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
