package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Dao.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Add_student extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	private JTextField text7;
	private JTextField text8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Add_student dialog = new Add_student();
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
	
	Connection con=Connect.DBConnector();
	
	public Add_student() {
		setTitle("Register New Student");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add_student.class.getResource("/images/book2.png")));
		setBounds(100, 100, 392, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name :");
			lblName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblName.setBounds(10, 11, 85, 30);
			contentPanel.add(lblName);
		}
		{
			JLabel lblSo = new JLabel("S/O :");
			lblSo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSo.setBounds(10, 48, 85, 30);
			contentPanel.add(lblSo);
		}
		{
			JLabel lblClass = new JLabel("Class :");
			lblClass.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblClass.setBounds(10, 89, 85, 30);
			contentPanel.add(lblClass);
		}
		{
			JLabel lblDateOfBirth = new JLabel("DOB :(YYYY/MM/DD)");
			lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 8));
			lblDateOfBirth.setBounds(10, 130, 85, 30);
			contentPanel.add(lblDateOfBirth);
		}
		{
			JLabel lblAddress = new JLabel("Address :");
			lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblAddress.setBounds(10, 171, 85, 30);
			contentPanel.add(lblAddress);
		}
		{
			JLabel lblStudentId = new JLabel("Student Id :");
			lblStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblStudentId.setBounds(10, 212, 85, 30);
			contentPanel.add(lblStudentId);
		}
		{
			JLabel lblUsername = new JLabel("UserName :");
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblUsername.setBounds(10, 253, 85, 30);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("PassWord :");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPassword.setBounds(10, 294, 85, 30);
			contentPanel.add(lblPassword);
		}
		{
			text1 = new JTextField();
			text1.setColumns(10);
			text1.setBounds(105, 11, 237, 30);
			contentPanel.add(text1);
		}
		{
			text2 = new JTextField();
			text2.setColumns(10);
			text2.setBounds(105, 48, 237, 30);
			contentPanel.add(text2);
		}
		{
			text3 = new JTextField();
			text3.setColumns(10);
			text3.setBounds(105, 89, 237, 30);
			contentPanel.add(text3);
		}
		{
			text4 = new JTextField();
			text4.setColumns(10);
			text4.setBounds(105, 130, 237, 30);
			contentPanel.add(text4);
		}
		{
			text5 = new JTextField();
			text5.setColumns(10);
			text5.setBounds(105, 171, 237, 30);
			contentPanel.add(text5);
		}
		{
			text6 = new JTextField();
			text6.setColumns(10);
			text6.setBounds(105, 212, 237, 30);
			contentPanel.add(text6);
		}
		{
			text7 = new JTextField();
			text7.setColumns(10);
			text7.setBounds(105, 253, 237, 30);
			contentPanel.add(text7);
		}
		{
			text8 = new JTextField();
			text8.setColumns(10);
			text8.setBounds(105, 294, 237, 30);
			contentPanel.add(text8);
		}
		{
			JButton button = new JButton("Done");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(text1.getText().equals("") || text2.getText().equals("")||text3.getText().equals("")||text4.getText().equals("")||text5.getText().equals("") || text6.getText().equals("")||text7.getText().equals("")||text8.getText().equals("")) 
					{
						JOptionPane.showMessageDialog(null, "All Fields are Mandatory");
					}
					else
					{
						try
						{
							String query="insert into student (name,fname,class,dob,address,id,username,password) values (?,?,?,?,?,?,?,?)";
							PreparedStatement pst=con.prepareStatement(query);
							pst.setString(1, text1.getText());
							pst.setString(2, text2.getText());
							pst.setString(3, text3.getText());
							pst.setString(4, text4.getText());
							pst.setString(5, text5.getText());
							pst.setString(6, text6.getText());
							pst.setString(7, text7.getText());
							pst.setString(8, text8.getText());
							int rs=pst.executeUpdate();
							if(rs!=0)
							{
								JOptionPane.showMessageDialog(null, "Student Record Added Successfully");
								text1.setText("");
								text2.setText("");
								text3.setText("");
								text4.setText("");
								text5.setText("");
								text6.setText("");
								text7.setText("");
								text8.setText("");
							}
							
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}
			});
			button.setBounds(253, 344, 89, 35);
			contentPanel.add(button);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(Add_student.class.getResource("/images/mountains-3236276.jpg")));
			label.setBounds(0, 0, 376, 390);
			contentPanel.add(label);
		}
	}

}
