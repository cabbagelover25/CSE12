package com.cse.ds;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private IService service;
	
	public void setService(IService service) {
		this.service = service;
	}
		
	public Response getStudent(Student student) throws IllegalArgumentException {
		if(student == null || student.getPID() == null)
		{
			throw new IllegalArgumentException("BAD_REQUEST");
		}
		
		Student obj = service.getStudent(student);
		Response response = new Response();
		if(obj == null)
		{
			response.setResponseCode(404);
			response.setResponseString("NOT_FOUND");
		}
		else
		{
			response.setResponseCode(200);
			response.setResponseString("OK");
			response.setContent(obj);
		}
		return response;
	}
	
	public Response addStudent(Student student) throws IllegalArgumentException {
		if(student == null || (student.getPID() == null && (student.getName() == null || student.getEmail() == null)))
		{
			throw new IllegalArgumentException("BAD_REQUEST");
		}
		Response response = new Response();
		if(service.addStudent(student) == true)
		{
			response.setResponseCode(201);
			response.setResponseString("CREATED");
		}
		else
		{
			response.setResponseCode(202);
			response.setResponseString("ACCEPTED"); // MODIFIED
		}
		return response;
	}

	public Response removeStudent(Student student) throws IllegalArgumentException {
		if(student == null || student.getPID() == null)
		{
			throw new IllegalArgumentException("BAD_REQUEST");
		}
		Response response = new Response();
		if(service.removeStudent(student) == true)
		{
			response.setResponseCode(200);
			response.setResponseString("OK");
		}
		else
		{
			response.setResponseCode(500);
			response.setResponseString("INTERNAL_ERROR");			
		}
		return response;
	}

	public Response updateStudent(Student student) throws IllegalArgumentException {
		if(student == null || student.getPID() == null)
		{
			throw new IllegalArgumentException("BAD_REQUEST");
		}
		Response response = new Response();
		if(service.updateStudent(student) == true)
		{
			response.setResponseCode(200);
			response.setResponseString("OK");
		}
		else
		{
			response.setResponseCode(404);
			response.setResponseString("NOT_FOUND");			
		}
		return response;
	}

	public Response getAllStudent() {
		List<Student> studList = service.getAllStudent();
		Response response = new Response();
		if(studList.size() == 0)
		{
			response.setResponseCode(404);
			response.setResponseString("NOT_FOUND");
			response.setListContent(new ArrayList<Student>());
		}
		else
		{
			response.setResponseCode(200);
			response.setResponseString("OK");
			response.setListContent(new ArrayList<Student>(studList));
		}
		return response;
	}
	
}
