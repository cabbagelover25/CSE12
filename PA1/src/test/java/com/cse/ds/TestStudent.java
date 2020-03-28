package com.cse.ds;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestStudent {

	static Student obj = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new Student();
	}

	@Test
	public void testGetPIDNull(){
		Assert.assertNull(obj.getPID());
	}

	@Test
	public void testGetPID(){
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertNotNull(stud1.getPID());
		Assert.assertEquals(stud1.getPID(), "1");
	}

	@Test
	public void testGetNameNull(){
		Assert.assertNull(obj.getName());
	}

	@Test
	public void testGetName(){
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertNotNull(stud1.getName());
		Assert.assertEquals(stud1.getName(), "name");
	}

	@Test
	public void testGetEmailNull(){
		Assert.assertNull(obj.getEmail());
	}

	@Test
	public void testGetEmail(){
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertNotNull(stud1.getEmail());
		Assert.assertEquals(stud1.getEmail(), "email");
	}

	@Test
	public void testHashCode() {
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertNotNull(stud1.hashCode());
	}

	//To String Tests
	@Test
	public void toStringNull(){
		Student stud1 = null;
		Assert.assertNull(stud1.toString());
	}

	@Test
	public void toStringNotNull() {
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertNotNull(stud1.toString());
	}

	/* These 3 tests are intended to check the equals method, 1 for a null object, 1 for the same object, and 1 for an object
	that has a different name but everything else remain the same */

	@Test
	public void testEquals() {
		Student stud1 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Student stud2 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertEquals(stud1.equals(stud2), true  );
	}

	@Test
	public void testNotEquals() {
		String stud1 = "Sam";
		Student stud2 = new Student("name", "1", "major", "minor", 4.1, "college", "email");
		Assert.assertEquals(stud1.equals(stud2), false );
	}

}
