package com.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.Dao.Connect;
import com.Service.Welcome;

public class Data {
	
	public static String name="";
	public static String fname="";
	public static String id="";
	public static String dob="";
	public static String address="";
	public static String username="";
	public static String password="";
	
	//Variables for Table Books
	public static String book_id="";
	public static String book_name="";
	public static String author="";
	public static String price="";
	public static String available="";
	
	//Variables for Table Books
	public static String book_name2="";
	public static String book_id2="";
	public static String book_author="";
	public static String student_name="";
	public static String student_id="";
	public static String date_issued="";
	public static String date_deposite="";
	
	//Variable used
	public static String book_search="";
	public static String student_search="";
	public static String select="";
	public static int temp=0;
	
	public static void main(String []args)
	{
		Data obj = new Data();
	}
}
