package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class dao 
{
	
	public int insert()
	{
		int check=0;
		Connection con=null;
		PreparedStatement pst=null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap65","root","root");
			String sql="insert into student values (?,?,?)";
			pst=con.prepareStatement(sql);
			pst.setInt(1,3);
			pst.setString(2,"abishek");
			pst.setDouble(3,61.00);
			check=pst.executeUpdate();
			
		}
		catch(ClassNotFoundException |  SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return check;
	}
	
	
	
	public List<student> display()
	{
		List<student> list=new ArrayList();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap65","root","root");
        String sql="select * from student";
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
        	student s=new student();
        	s.setId((int)rs.getObject("id"));
        	s.setName((String)rs.getObject("name"));
        	s.setSpercentage((double)rs.getObject("spercentage"));
        	list.add(s);
        	
        }
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
			rs.close();
			pst.close();
			con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		
		return list;
        
	}
	
	
	public int deleteStudent(int id)
	{
		int check=0;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jap65","root","root");
			String sql="delete from student where id=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,3);
			check=pst.executeUpdate();
			
		} catch (ClassNotFoundException |SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
			pst.close();
			con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return check;
	}

}
