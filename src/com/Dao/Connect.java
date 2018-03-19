package com.Dao;
import com.Constants.Message;
import com.utils.Data;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class Connect {

static String jdbc_driver="com.mysql.jdbc.Driver";


public static Connection DBConnector()
{
	try
	{
		Class.forName(jdbc_driver);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","SHIVAM");
		if(con!=null)
		{
			return con;
		}
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, Message.CONNECTION_FAIL);
	}
	return null;
}
}

