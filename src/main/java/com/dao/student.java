package com.dao;

public class student {
	
	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + ", spercentage=" + spercentage + "]";
	}
	private int id;
	private String name;
	private double spercentage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSpercentage() {
		return spercentage;
	}
	public void setSpercentage(double spercentage) {
		this.spercentage = spercentage;
	}
	
	

}
