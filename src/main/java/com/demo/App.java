package com.demo;

import java.util.List;

import com.dao.dao;
import com.dao.student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        dao d=new dao();
        System.out.println(d.deleteStudent(3));
        
        
    }
}
