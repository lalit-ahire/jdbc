package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.dao.StudentDao;



public class App{
	
	public static void main(String[] args)                                                          
	{
		student s=new student();
		
	StudentDao sd=new StudentDao();
	s.setSpercentage(80.15);
	List<student> list=sd.findbyany(s);
	for(student t:list)
		System.out.println(t);
		
	    
	}
}
   

