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
import java.awt.JobAttributes;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Add_book extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField field;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Add_book dialog = new Add_book();
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
	
	public Add_book() {
		setTitle("Add Book");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Add_book.class.getResource("/images/book2.png")));
		setBounds(100, 100, 383, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBookName = new JLabel("Book Name :");
			lblBookName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblBookName.setBounds(10, 11, 85, 30);
			contentPanel.add(lblBookName);
		}
		{
			JLabel lblAuthor = new JLabel("Author :");
			lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblAuthor.setBounds(10, 52, 85, 30);
			contentPanel.add(lblAuthor);
		}
		{
			JLabel lblPrice = new JLabel("Price :");
			lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrice.setBounds(10, 93, 85, 30);
			contentPanel.add(lblPrice);
		}
		{
			JLabel lblQuantity = new JLabel("Quantity :");
			lblQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
			lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblQuantity.setBounds(10, 134, 85, 30);
			contentPanel.add(lblQuantity);
		}
		{
			field = new JTextField();
			field.setBounds(105, 11, 237, 30);
			contentPanel.add(field);
			field.setColumns(10);
		}
		{
			field2 = new JTextField();
			field2.setColumns(10);
			field2.setBounds(105, 52, 237, 30);
			contentPanel.add(field2);
		}
		{
			field3 = new JTextField();
			field3.setColumns(10);
			field3.setBounds(105, 93, 237, 30);
			contentPanel.add(field3);
		}
		{
			field4 = new JTextField();
			field4.setColumns(10);
			field4.setBounds(105, 134, 237, 30);
			contentPanel.add(field4);
		}
		{
			JButton btnDone = new JButton("Done");
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(field.getText().equals("") || field2.getText().equals("")||field3.getText().equals("")||field4.getText().equals("")) 
					{
						JOptionPane.showMessageDialog(null, "All Fields are Mandatory");
					}
					else
					{
						try
						{
							int rs=0;
							String query="insert into books (book_name,author,price,available) values (?,?,?,?)";
							PreparedStatement pst=con.prepareStatement(query);
							pst.setString(1, field.getText());
							pst.setString(2, field2.getText());
							pst.setString(3, field3.getText());
							pst.setString(4, field4.getText());
							rs=pst.executeUpdate();
							if(rs!=0)
							{
								JOptionPane.showMessageDialog(null, "Book Added SucessFully");
								field.setText("");
								field2.setText("");
								field3.setText("");
								field4.setText("");
							}
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, e);
						}
					}
				}
			});
			btnDone.setBounds(253, 200, 89, 35);
			contentPanel.add(btnDone);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(Add_book.class.getResource("/images/mountains-3236276.jpg")));
			label.setBounds(0, 0, 368, 262);
			contentPanel.add(label);
		}
	}

}
