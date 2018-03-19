package com.Service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Dao.Connect;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.utils.Data;

import net.proteanit.sql.DbUtils;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.DataBufferUShort;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

@SuppressWarnings("unused")
public class Home_Student extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Home_Student dialog = new Home_Student();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Connection con=Connect.DBConnector();
	static String username="";
	static String password="";
	static String student_id;
	static String student_name;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	
	
	/**
	 * Create the dialog.
	 */
	public Home_Student() {
		setTitle("Student");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Student.class.getResource("/images/book2.png")));
		setBounds(100, 100, 826, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setLocation(0,0);
		
		JLabel label = new JLabel("Recent Issued Books");
		label.setBounds(0, 33, 810, 20);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(label);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setBounds(405, 70, 70, 17);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblId);
		
		table = new JTable();
		table.setBounds(10, 129, 790, 120);
		contentPanel.add(table);
		try
		{
			String query="select *from current_issued where student_id=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, Data.id);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}

			
			JLabel lblName = new JLabel("Name :");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setBounds(10, 70, 45, 17);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblName);
			
			JLabel lblSo = new JLabel("S/O :");
			lblSo.setBounds(20, 98, 70, 17);
			lblSo.setHorizontalAlignment(SwingConstants.LEFT);
			lblSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblSo);
			
			
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setColumns(10);
			textField_1.setBounds(72, 98, 246, 20);
			contentPanel.add(textField_1);
			
			textField = new JTextField();
			textField.setEditable(false);
			textField.setBounds(72, 70, 246, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(username);
			
			textField_2 = new JTextField();
			textField_2.setEditable(false);
			textField_2.setColumns(10);
			textField_2.setBounds(542, 70, 125, 20);
			contentPanel.add(textField_2);
			textField_2.setText(student_id);
			
			JLabel lblDob = new JLabel("DOB :");
			lblDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblDob.setBounds(405, 101, 70, 17);
			contentPanel.add(lblDob);
			
			textField_3 = new JTextField();
			textField_3.setText("");
			textField_3.setEditable(false);
			textField_3.setColumns(10);
			textField_3.setBounds(542, 98, 125, 20);
			contentPanel.add(textField_3);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 820, 22);
			contentPanel.add(menuBar);
			
			JMenu mnSearch = new JMenu("Search");
			menuBar.add(mnSearch);
			
			JMenuItem mntmBook = new JMenuItem("Book");
			mntmBook.setHorizontalAlignment(SwingConstants.CENTER);
			mntmBook.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Data.select="student_search";
					Search.main(null);
				}
			});
			mnSearch.add(mntmBook);
			
			JMenu mnView = new JMenu("View");
			menuBar.add(mnView);
			
			JMenuItem mntmAllBooks = new JMenuItem("All Books");
			mntmAllBooks.addActionListener(new ActionListener() {
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
			mnView.add(mntmAllBooks);
			
			JMenu mnUser = new JMenu("User");
			menuBar.add(mnUser);
			
			JMenuItem mntmLogout = new JMenuItem("Logout");
			mntmLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					Welcome.main(null);
				}
			});
			mnUser.add(mntmLogout);
			
			textField.setText(Data.name);
			textField_1.setText(Data.fname);
			textField_2.setText(Data.id);
			textField_3.setText(Data.dob);
			
			JLabel lblJkvhdfs = new JLabel("jkvhdfs");
			lblJkvhdfs.setIcon(new ImageIcon(Home_Student.class.getResource("/images/mountains-3236276.jpg")));
			lblJkvhdfs.setBounds(0, 22, 810, 238);
			contentPanel.add(lblJkvhdfs);
			
			
			
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
}
