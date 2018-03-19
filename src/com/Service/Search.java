package com.Service;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.utils.Data;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Search extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Search dialog = new Search();
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
	public Search() {
		setTitle("Search");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Search.class.getResource("/images/book2.png")));
		setBounds(100, 100, 320, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEnterKeyword = new JLabel("Enter KeyWord :");
		lblEnterKeyword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnterKeyword.setBounds(10, 11, 116, 35);
		contentPanel.add(lblEnterKeyword);
		
		textField = new JTextField();
		textField.setBounds(114, 11, 171, 35);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Data.select.equals("student_search"))
				{
					Data.book_search=textField.getText();
					textField.setText("");
					dispose();
					Home_Student.result();
				}
				else if(Data.select.equals("admin_search"))
						{
							Data.book_search=textField.getText();
							textField.setText("");
							dispose();
							Home_Admin.result();
						}
				else if(Data.select.equals("admin_student"))
				{
					Data.student_search=textField.getText();
					textField.setText("");
					dispose();
					Home_Admin.search_student();
				}
				else if(Data.select.equals("book_fetch"))
				{
						Data.book_search=textField.getText();
						textField.setText("");
						dispose();
						Book_issue.result();
				}
				
			}
		});
		btnSearch.setBounds(187, 122, 107, 23);
		contentPanel.add(btnSearch);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Search.class.getResource("/images/mountains-3236276.jpg")));
		label.setBounds(0, 0, 304, 168);
		contentPanel.add(label);
	}
}
