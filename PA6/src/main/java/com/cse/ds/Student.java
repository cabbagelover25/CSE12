package com.cse.ds;
public class Student {

	private String name;
	private String PID;
	private String major;
	private String minor;
	private Double CGPA;
	private String college;
	private String email;
	
	public Student() {
	}
	
	public Student(String name, String pID, String major, String minor, Double cGPA, String college, String email) {
		this.name = name;
		this.PID = pID;
		this.major = major;
		this.minor = minor;
		this.CGPA = cGPA;
		this.college = college;
		this.email = email;
	}
	
	public String getPID()
	{
		return getPID();
	}
	
	public String getName()
	{
		return getName();
	}
	
	public String getEmail()
	{
		return getEmail();
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
