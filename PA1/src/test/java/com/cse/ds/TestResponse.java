package com.cse.ds;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestResponse {

	static Response obj = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		obj = new Response();
	}

	@Before
	public void setUpBeforeMethod() {
		obj.setContent(null);
		obj.setListContent(null);
		obj.setResponseCode(null);
		obj.setResponseString(null);
	}

	@Test
	public void testGetResponseCodeNull() {
		Assert.assertNull(obj.getResponseCode()); // Test to make sure getResponse Code is properly set to null
	}

	@Test
	public void testGetResponseCodeNotNull() {
		obj.setResponseCode(200); /* Test to see that if you change response code from null to 200, it will now show up
		as 200, so this one is checking the set actually while the first was setting the get. */
		Assert.assertEquals((int) obj.getResponseCode(), 200);
	}

	@Test
	public void testGetResponseStringNull(){
		Assert.assertNull(obj.getResponseString());
	}

	@Test
	public void testGetResponseStringNotNull(){
		obj.setResponseString("panda");
		Assert.assertEquals((String) obj.getResponseString(), "panda") ;
	}

	@Test
	public void testGetListContentNull() {
		Assert.assertNull(obj.getListContent());
	}

	@Test
	public void testGetListContentNotNull() {
		Student stud1, stud2;
		stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		stud2 = new Student("name", "2", "major", "minor", 4.1, "college", "email");
		List<Student> studList = Arrays.asList(stud1, stud2);
		obj.setListContent(studList);

		List<Student> respStudList = obj.getListContent();
		Assert.assertNotNull(respStudList);
		Assert.assertEquals(respStudList.size(), studList.size());
		Assert.assertTrue(respStudList.get(0).equals(studList.get(0)));
		Assert.assertTrue(respStudList.get(1).equals(studList.get(1)));
	}

	@Test
	public void testGetStudentContentNull(){
		Assert.assertNull(obj.getContent());
	}

	@Test
	public void testGetStudentContentNotNull(){
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		obj.setContent(stud1);

		Student respStud1 = obj.getContent();
		Assert.assertNotNull(respStud1);

	}

}
