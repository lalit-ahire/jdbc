package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.student;

public class myDatabase {
	
	public static Connection myConnection()
	{
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAP63","root","root");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return con;
	}
	
	public static void closeConnection(PreparedStatement pst,Connection con)
	{
		try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(PreparedStatement pst,Connection con,ResultSet rs)
	{
		
		try {
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     public static List<student> studentMapper(ResultSet rs)	
     { List<student> list=new ArrayList();
    	 try {
			while(rs.next()) {
				 
				student s=new student();
				s.setSid((int)rs.getObject("sid"));
				s.setSname((String)rs.getObject("sname"));
				s.setScity((String)rs.getObject("Scity"));
				s.setSpercentage((double)rs.getObject("spercentage"));
				list.add(s);
				
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	 return list;
     }

}
