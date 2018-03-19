package com.utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.naming.spi.DirStateFactory.Result;
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
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Modify_book extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField text1;
	private JTextField text6;
	private JTextField text7;
	private JTextField text8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Modify_book dialog = new Modify_book();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Connection con=Connect.DBConnector();
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text9;
	private JTextField text10;
	
	/**
	 * Create the dialog.
	 */
	public Modify_book() {
		setTitle("Details Update");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Modify_book.class.getResource("/images/book2.png")));
		setBounds(100, 100, 404, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEnterBookId = new JLabel("Enter Book ID :");
		lblEnterBookId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterBookId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterBookId.setBounds(10, 61, 101, 29);
		contentPanel.add(lblEnterBookId);
		
		JLabel lblBookName = new JLabel("Book Name :");
		lblBookName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBookName.setBounds(10, 101, 101, 29);
		contentPanel.add(lblBookName);
		
		JLabel lblAuthor = new JLabel("Author :");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAuthor.setBounds(10, 141, 101, 29);
		contentPanel.add(lblAuthor);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(10, 181, 101, 29);
		contentPanel.add(lblPrice);
		
		text1 = new JTextField();
		text1.setBounds(121, 62, 228, 29);
		contentPanel.add(text1);
		text1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Match Details Of Book :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 184, 29);
		contentPanel.add(lblNewLabel);
		
		JButton btnFetch = new JButton("Get Deatils");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				text2.setText("");
				text3.setText("");
				text4.setText("");
				text10.setText("");
				try
				{
					String temp="";
					String query="select *from books where book_id=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, text1.getText());
					ResultSet rs=pst.executeQuery();
					while(rs.next())
					{
						text2.setText(rs.getString("book_name"));
						text3.setText(rs.getString("author"));
						text4.setText(rs.getString("price"));
						text10.setText(rs.getString("available"));
						temp=rs.getString("book_name");
					}
					if(temp.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Book Not Found");
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnFetch.setBounds(260, 263, 89, 23);
		contentPanel.add(btnFetch);
		
		JLabel lblEnterNewDetail = new JLabel("Enter New Detail :");
		lblEnterNewDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterNewDetail.setBounds(10, 287, 184, 29);
		contentPanel.add(lblEnterNewDetail);
		
		JLabel label_1 = new JLabel("Book Name :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 327, 101, 29);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Author :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 367, 101, 29);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Price :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(10, 407, 101, 29);
		contentPanel.add(label_3);
		
		text6 = new JTextField();
		text6.setColumns(10);
		text6.setBounds(121, 328, 228, 29);
		contentPanel.add(text6);
		
		text7 = new JTextField();
		text7.setColumns(10);
		text7.setBounds(121, 368, 228, 29);
		contentPanel.add(text7);
		
		text8 = new JTextField();
		text8.setColumns(10);
		text8.setBounds(121, 408, 228, 29);
		contentPanel.add(text8);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt=null;
				
				try
				{
					int rs=0;
					String query2="update books set book_name='"+text6.getText()+"', author='"+text7.getText()+"', price='"+text8.getText()+"',available='"+text9.getText()+"' where book_id="+text1.getText();
					stmt=con.createStatement();	
					rs=stmt.executeUpdate(query2);
					if(rs!=0)
					{
						JOptionPane.showMessageDialog(null, "Updated SuccessFully");
						text1.setText("");
						text2.setText("");
						text3.setText("");
						text4.setText("");
						text6.setText("");
						text7.setText("");
						text8.setText("");
						text9.setText("");
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnUpdate.setBounds(260, 496, 89, 23);
		contentPanel.add(btnUpdate);
		
		text2 = new JTextField();
		text2.setEditable(false);
		text2.setColumns(10);
		text2.setBounds(121, 106, 228, 29);
		contentPanel.add(text2);
		
		text3 = new JTextField();
		text3.setEditable(false);
		text3.setColumns(10);
		text3.setBounds(121, 146, 228, 29);
		contentPanel.add(text3);
		
		text4 = new JTextField();
		text4.setEditable(false);
		text4.setColumns(10);
		text4.setBounds(121, 186, 228, 29);
		contentPanel.add(text4);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity.setBounds(10, 447, 101, 29);
		contentPanel.add(lblQuantity);
		
		text9 = new JTextField();
		text9.setColumns(10);
		text9.setBounds(121, 448, 228, 29);
		contentPanel.add(text9);
		
		JLabel lblQuantity_1 = new JLabel("Quantity :");
		lblQuantity_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantity_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantity_1.setBounds(10, 221, 101, 29);
		contentPanel.add(lblQuantity_1);
		
		text10 = new JTextField();
		text10.setEditable(false);
		text10.setColumns(10);
		text10.setBounds(121, 226, 228, 29);
		contentPanel.add(text10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Modify_book.class.getResource("/images/mountains-3236276.jpg")));
		label.setBounds(0, 0, 388, 530);
		contentPanel.add(label);
	}
}
