package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.student;
import com.util.myDatabase;

public class StudentDao {
	
	public int  insertStudent(student s)
	{
		int check=0;
      String sql="insert into student(sname,scity,spercentage) values(?,?,?)";
      try (Connection con=myDatabase.myConnection();
    		  PreparedStatement pst=con.prepareStatement(sql);){
    	 pst.setString(1,s.getSname());
    	 pst.setString(2,s.getScity());
    	 pst.setDouble(3,s.getSpercentage());
    	check= pst.executeUpdate();
    	  
      }catch(SQLException e) {
    	  e.printStackTrace();
    	  
      }
		
		
		
		
		return check;
	}
	
	public int deletestudentbySid(int sid)
	{
		int check=0;
	
		String sql="delete from student where sid=?";
		try (Connection con=myDatabase.myConnection();
			PreparedStatement pst=con.prepareStatement(sql);){
			pst.setInt(1,sid);
         check=pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		return check;
	}

	public int updateStudent(String sname,int sid)

	{
		int check=0;
		Connection con=null;
	PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAP63","root","root");
			String sql="update student set sname=? where sid=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,sname);
			pst.setInt(2, sid);
			check=pst.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return check;
	}
   
	public student findstudent(int sid)
	{
		student s=null;
		String sql="select sid,sname,scity,spercentage from student where sid=?";
		try(Connection con=myDatabase.myConnection();
				PreparedStatement pst=con.prepareStatement(sql);){
			pst.setInt(1,sid);
			ResultSet rs=pst.executeQuery();
			List<student> list=myDatabase.studentMapper(rs);
			if(!list.isEmpty())
				s=list.get(0);
			rs.close();
			
		}
		catch(SQLException e){
			System.out.println(e);
			
		}
		return s;
		
		
		
	}
    public List<student> findAllStudents()
    {
    	List<student> list=new ArrayList<>();
    	String sql="select sid,sname,scity,spercentage from student";
    	try(Connection con=myDatabase.myConnection();
    			PreparedStatement pst=con.prepareStatement(sql);
    			ResultSet rs=pst.executeQuery();){
    		list.addAll(myDatabase.studentMapper(rs));
    		rs.close();
    		
    	}
    	catch(SQLException e){ e.printStackTrace();
    		
    	}
    
    	
    	
    
    return list;}
  public int update(student s)
  {
	  int check=0;
	  Connection con=null;
	  PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAP63","root","root");
			String sql="update student set sname=?,scity=? where sid=?";
			pst=con.prepareStatement(sql);
			pst.setString(1,s.getSname());
		
			pst.setString(2,s.getScity());
			pst.setInt(3,s.getSid());
			check=pst.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
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

public List<student> findstudentonpercentage(double low,double high)
{
	List<student> list=new ArrayList<>();
	String sql="select sname ,scity where spercentage between ? and ?";
	try(Connection con=myDatabase.myConnection();
			PreparedStatement pst=con.prepareStatement(sql);) {
		pst.setDouble(1,low);
		pst.setDouble(2,high);
		ResultSet rs=pst.executeQuery();
		list.addAll(myDatabase.studentMapper(rs));
		
		
		
		
	}catch(SQLException e){e.printStackTrace();
		
	}
	
	
	
	
	return list;
}
 
public List<student> findbysname(String s)
{
	List<student> list=new ArrayList();
	
	String sql="select sname,scity,spercentage from student where sname like ?";
	
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet r=null;
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/JAP63","root","root");
		pst=con.prepareStatement(sql);
		pst.setString(1,s+"%");
		r=pst.executeQuery();
		while(r.next())
		{
			student s1=new student();
			s1.setSname((String)r.getObject("sname"));
			s1.setScity((String)r.getObject("scity"));
			s1.setSpercentage((double)r.getObject("spercentage"));
			list.add(s1);
			
		}
		} catch (ClassNotFoundException|SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	return list;
}


public List<student> findbyany(student s)
{
	List<student> list=new ArrayList();
	String sql="select sid,sname,spercentage,scity from student where sid=? or sname=? or spercentage=? ";
	try(Connection con=myDatabase.myConnection();
			PreparedStatement pst=con.prepareStatement(sql);) {
		pst.setString(1,s.getSname());
		pst.setString(2,s.getScity());
		pst.setDouble(3,s.getSpercentage());
		ResultSet rs=pst.executeQuery();
		list.addAll(myDatabase.studentMapper(rs));
	}
	catch(SQLException e){ e.printStackTrace();
		
	}
	
	
 return list;
}
public List<student> findbyspercentage(double s)
{
	List<student> l= new ArrayList();
	String sql="select sid,scity,spercentage,sname from student where spercentage<=?";
	try(Connection con=myDatabase.myConnection();
			PreparedStatement pst=con.prepareStatement(sql);) {
		pst.setDouble(1,s);
		ResultSet rs=pst.executeQuery();
		l.addAll(myDatabase.studentMapper(rs));
		rs.close();
	}catch(SQLException e){e.printStackTrace();
		
	}
	
	return l;
}

}
