package com.demo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

class stud
{ 
	private String name,city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
class studao
{

	public int insertdata(stud s)
	{
		int check=0;
		Connection con=null;
		PreparedStatement pst=null;
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/JAP63","root","root");
			String sql="insert into t1(name,city) values(?,?)";
			pst=con.prepareStatement(sql);
			pst.setString(1,s.getName());
			pst.setString(2,s.getCity());
			check=pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{ try {
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		
		
		
		return check;
	}
}

public class app2 {

	public static void main(String[] args) {
		
		stud s=new stud();
		studao sd=new studao();
		s.setName("rishbh");
		s.setCity("vardha");
		System.out.println(sd.insertdata(s));

	}

}
