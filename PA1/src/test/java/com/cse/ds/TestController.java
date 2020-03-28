package com.cse.ds;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Matchers;
import java.util.ArrayList;
import java.util.List;

public class TestController {
	
	static Controller obj = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new Controller();
	}
		
	@Test (expected =  IllegalArgumentException.class)
	public void testGetNullStudent() throws Exception {
		obj.getStudent(null); // throws null if student or PID is null which we then catch
	}

	@Test (expected =  IllegalArgumentException.class)
	public void testGetNullStudentPID() throws Exception {
		Student stud = Mockito.mock(Student.class); //1
		Mockito.when(stud.getPID()).thenReturn(null);
		obj.getStudent(stud); // throws null if student or PID is null which we then catch
	}

	@Test
	public void testGetStudentFound() {
		Student stud = Mockito.mock(Student.class); //1
		Mockito.when(stud.getPID()).thenReturn("1"); //2
		//Your not testing the PID function so it should return the right think obvi

		IService serv = Mockito.mock(IService.class); //3
		Mockito.when(serv.getStudent(Matchers.any(Student.class)))
				.thenReturn(stud); //4

		obj.setService(serv); //5
		Response res = obj.getStudent(stud); /* So this always returns stud making sure that the service wors
		*/
		Assert.assertNotNull(res); //7
		Assert.assertEquals((int)res.getResponseCode(), 200); //8
		Assert.assertEquals(res.getResponseString().toString(),"OK"); //9
		Assert.assertEquals(((Student)res.getContent()).getPID(),"1");//10
	}
	
	@Test
	public void testGetStudentFoundObjNull() {
		Student stud = Mockito.mock(Student.class); //1
		Mockito.when(stud.getPID()).thenReturn("1"); //2
		//Your not testing the PID function so it should return the right think obvi
		IService serv = Mockito.mock(IService.class); //3
		Mockito.when(serv.getStudent(Matchers.any(Student.class)))
				.thenReturn(null);
		//4 If youre inputting a student for serv, return null
		// How can I get a test that works not when the function is first called but only inside the function
		obj.setService(serv); //obj has it's service being set so that when the mockito right above is called then the object will be null
		Response res = obj.getStudent(stud);
		/*Res is not null and obj is not null, the only thing that's null is
		* stud because */
		Assert.assertNotNull(res); //7
		Assert.assertEquals((int)res.getResponseCode(), 404); //8
		Assert.assertEquals(res.getResponseString().toString(),"NOT_FOUND"); //9
	}

	@Test (expected =  IllegalArgumentException.class)
	public void addNullStudent() throws Exception {

		obj.addStudent(null); // throws null if student or PID is null which we then catch
	}

	@Test (expected = IllegalArgumentException.class)
	public void nullPIDAndNameAddStudent(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		Mockito.when(stud.getName()).thenReturn(null);
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");
		obj.addStudent(stud);
	}

	@Test (expected = IllegalArgumentException.class)
	public void nullPIDAndEmail(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn(null);
		obj.addStudent(stud);
	}

	@Test
	public void newAdditionAddStudent(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.addStudent(Matchers.any(Student.class))).thenReturn(true);
		obj.setService(serv);
		Response res = obj.addStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 201);
		Assert.assertEquals(res.getResponseString(), "CREATED");
	}

	@Test
	public void modifiedAdditionAddStudent(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("2");
		Mockito.when(stud.getName()).thenReturn("George");
		Mockito.when(stud.getEmail()).thenReturn("ModifiedGeorge@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.addStudent(Matchers.any(Student.class))).thenReturn(false);
		obj.setService(serv);
		Response res = obj.addStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 202);
		Assert.assertEquals(res.getResponseString(), "ACCEPTED");
	}

	@Test (expected =  IllegalArgumentException.class)
	public void removeNullStudent() throws Exception {
		obj.removeStudent(null); // throws null if student or PID is null which we then catch
	}

	@Test
	public void removeStudentSuccess(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.removeStudent(Matchers.any(Student.class))).thenReturn(true);
		obj.setService(serv);
		Response res = obj.removeStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 200);
		Assert.assertEquals(res.getResponseString(), "OK");
	}

	@Test
	public void removeStudentFailure(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.removeStudent(Matchers.any(Student.class))).thenReturn(false);
		obj.setService(serv);
		Response res = obj.removeStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 500);
		Assert.assertEquals(res.getResponseString(), "INTERNAL_ERROR");
	}

	@Test (expected =  IllegalArgumentException.class)
	public void updateNullStudent() throws Exception {
		obj.updateStudent(null); // throws null if student or PID is null which we then catch
	}

	@Test (expected =  IllegalArgumentException.class)
	public void updateNullStudentPID() throws Exception {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		obj.updateStudent(stud); // throws null if student or PID is null which we then catch
	}

	@Test
	public void updateStudentSuccess(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.updateStudent(Matchers.any(Student.class))).thenReturn(true);
		obj.setService(serv);
		Response res = obj.updateStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 200);
		Assert.assertEquals(res.getResponseString(), "OK");
	}

	@Test
	public void updateStudentFailure(){
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("Sam");
		Mockito.when(stud.getEmail()).thenReturn("generic@gmail.com");

		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.updateStudent(Matchers.any(Student.class))).thenReturn(false);
		obj.setService(serv);
		Response res = obj.updateStudent(stud);

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 404);
		Assert.assertEquals(res.getResponseString(), "NOT_FOUND");
	}

	@Test
	public void getAllStudentNotEmpty(){
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		List<Student> studList = new ArrayList<Student>();
		studList.add(stud1);
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getAllStudent()).thenReturn(studList);

		obj.setService(serv);
		Response res = obj.getAllStudent();

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 200);
		Assert.assertEquals(res.getResponseString(), "OK");

	}

	@Test
	public void getAllStudentEmpty(){
		List<Student> studList = new ArrayList<Student>();
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getAllStudent()).thenReturn(studList);

		obj.setService(serv);
		Response res = obj.getAllStudent();

		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 404);
		Assert.assertEquals(res.getResponseString(), "NOT_FOUND");

	}





	//service is a fake mock thing its not this.addStudent its add student



}
