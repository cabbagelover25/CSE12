package com.cse.ds.models;

import java.io.Serializable;

/**
 * 
 * @author harsh
 *
 */
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	String name;
	String PID;
	String major;
	String minor;
	Double CGPA;
	String college;
	String email;
	
	public Student() {
	}
	
	public Student(String pID, Double cGPA) {
		this.PID = pID;
		this.CGPA = cGPA;
	}
	
	public String getPID()
	{
		return PID;
	}
	
	public Double getCGPA()
	{
		return CGPA;
	}
		
	@Override
	public String toString() {
		String format = "name:%s|PID:%s|major:%s|minor:%s|CGPA:%f|college:%s|email:%s";
		return String.format(format, name, PID, major, minor, CGPA, college,email);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student)
			return (this.PID).equalsIgnoreCase(((Student) obj).PID);
		else
			return false;
	}

	@Override
	public int hashCode() {
		return (this.PID.toLowerCase()).hashCode();
	}
}
