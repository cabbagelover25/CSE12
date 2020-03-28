package com.cse.ds;
import java.util.List;

public interface IService {
	// So just an interfact the student takes from
	public Student getStudent(Student student);
	public boolean addStudent(Student student);
	public boolean removeStudent(Student student);
	public boolean updateStudent(Student student);
	public List<Student> getAllStudent();
}
