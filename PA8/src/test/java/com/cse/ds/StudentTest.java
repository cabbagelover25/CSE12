/**
 * Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: May 31, 2019
 *
 * Contains StudentTest class which is a Tester class for BinarySearchTree.
 */
package com.cse.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.internal.matchers.Null;

/**
 * Tests for functionality in all methods of BinarySearchTree as well as as
 * many edge cases as possible.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentTest{
	
	static BinarySearchTree bst = null;

	/**
	 * Set-up method before method is run
	 */
	@BeforeClass
    public static void setUpBeforeClass() {
		bst = new BinarySearchTree(null, null);
		bst.addCity("A", 5);
		bst.addCity("B", 4);
		bst.addCity("C", 8);
		bst.addCity("D", 2);
		bst.addCity("E", 7);
    }

	/**
	 * Tester method before method is run
	 */
	@Before
	public void beforeMethod()
	{
		bst = new BinarySearchTree(null, null);
		bst.addCity("A", 5);
		bst.addCity("B", 4);
		bst.addCity("C", 8);
		bst.addCity("D", 2);
		bst.addCity("E", 7);
	}

	/**
	 * Tester method for First constructur
	 */
	@Test
	public void FirstNodeConstructorTest(){
		Node node1 = new Node("Chicago", 9000);
		Assert.assertEquals(node1.city, "Chicago");
		Assert.assertEquals(node1.population, 9000);
	}

	/**
	 * Tester method for toString and second constructor.
	 */
	@Test
	public void SecondNodeConstructorTest() {
		bst = new BinarySearchTree("resource/input.txt", 5);
		System.out.println(bst.toString() + "CHECK");
		Assert.assertEquals("|Chicago city_Illinois:2695598\n" + 
				"|L:\n" + 
				"|-----Phoenix city_Arizona:1445632\n" + 
				"|-----L:\n" + 
				"|----------NULL\n" + 
				"|-----R:\n" + 
				"|----------Houston city_Texas:2099451\n" + 
				"|----------L:\n" + 
				"|---------------NULL\n" + 
				"|----------R:\n" + 
				"|---------------NULL\n" + 
				"|R:\n" + 
				"|-----Los Angeles city_California:3792621\n" + 
				"|-----L:\n" + 
				"|----------NULL\n" + 
				"|-----R:\n" + 
				"|----------New York city_New York:8175133\n" + 
				"|----------L:\n" + 
				"|---------------NULL\n" + 
				"|----------R:\n" + 
				"|---------------NULL\n", bst.toString());
		System.out.println(bst.getTopView());
	}

	/**
	 * Tester method for getSortedCities, getPreOrderTraversal, and
	 * getPostOrderTraversal
	 */
	@Test
	public void TraversalsTest( ) {
		Assert.assertEquals(Arrays.asList(new String[]{"D", "B", "A", "E", "C"}),bst.getSortedCities());
		Assert.assertEquals(Arrays.asList(new String[]{"A", "B", "D", "C", "E"}),bst.getPreOrderTraversal());
		Assert.assertEquals(Arrays.asList(new String[]{"D", "B", "E", "C", "A"}),bst.getPostOrderTraversal());
	}

	/**
	 * Tester for getSmallestCity and getLargestCity methods
	 */
	@Test
	public void CitiesTest( ) {
		Assert.assertEquals("D", bst.getSmallestCity());
		Assert.assertEquals("C", bst.getLargestCity());
	}

	/**
	 * Tester for getMaxDepth and getMaxWidth functions
	 */
	@Test
	public void MaxWidthAndDepthTest( ) {
		Assert.assertEquals(3, bst.getMaxDepth());
		Assert.assertEquals(3, bst.getMaxWidth());
	}

	/**
	 * Tester for LevelWiseCities function
	 */
	@Test
	public void LevelWiseCitiesTest( ) {
		List<List<String>> res = new ArrayList<>();
		res.add(Arrays.asList(new String[]{"A"}));
		res.add(Arrays.asList(new String[]{"B","C"}));
		res.add(Arrays.asList(new String[]{"D","E"}));
		System.out.println(res.toString());
		Assert.assertEquals(res,bst.getLevelWiseCities());
	}

	/**
	 * Tester for getAllPaths and getTwistyTraversals method.
	 */
	@Test
	public void AllPathsTest( ) {
		Assert.assertEquals(Arrays.asList(new String[]{"=>A=>B=>D", "=>A=>C=>E"}),bst.getAllPaths());
		List<List<String>> res1 = new ArrayList<>();
		res1.add(Arrays.asList(new String[]{"A"}));
		res1.add(Arrays.asList(new String[]{"C","B"}));
		res1.add(Arrays.asList(new String[]{"D","E"}));
		Assert.assertEquals(res1,bst.getTwistyTraversal());
	}

	/**
	 * Tester for TwistyTraversal method.
	 */
	@Test
	public void TwistyTraversalTest( ) {
		List<List<String>> res1 = new ArrayList<>();
		res1.add(Arrays.asList(new String[]{"A"}));
		res1.add(Arrays.asList(new String[]{"C","B"}));
		res1.add(Arrays.asList(new String[]{"D","E"}));
		Assert.assertEquals(res1,bst.getTwistyTraversal());
	}

	/**
	 * Tester for getBSTilt methods.
	 */
	@Test
	public void TiltTest( ) {
		Assert.assertEquals(18, bst.getBSTilt());
	}

	/**
	 * Tester for getRightView and getLeftView methods.
	 */
	@Test
	public void VerticalTests( ) {
		Assert.assertEquals(Arrays.asList(new String[]{"A", "C", "E"}), bst.getRightView());
		Assert.assertEquals(Arrays.asList(new String[]{"A", "B", "D"}), bst.getLeftView());
	}

	/**
	 * Tester for getTopView and getBottomView methods.
	 */
	@Test
	public void HorizontalTests( ) {
		Assert.assertEquals(Arrays.asList(new String[]{"D", "B", "A", "C"}), bst.getTopView());
		Assert.assertEquals(Arrays.asList(new String[]{"D", "B", "E", "C"}), bst.getBottomView());
	}

	/**
	 * Tester for flattenToLL method
	 */
	@Test
	public void LinkedListTest( ) {
		bst.flattenToLL();
		Assert.assertEquals("|A:5\n" + 
				"|L:\n" + 
				"|-----NULL\n" + 
				"|R:\n" + 
				"|-----B:4\n" + 
				"|-----L:\n" + 
				"|----------NULL\n" + 
				"|-----R:\n" + 
				"|----------D:2\n" + 
				"|----------L:\n" + 
				"|---------------NULL\n" + 
				"|----------R:\n" + 
				"|---------------C:8\n" + 
				"|---------------L:\n" + 
				"|--------------------NULL\n" + 
				"|---------------R:\n" + 
				"|--------------------E:7\n" + 
				"|--------------------L:\n" + 
				"|-------------------------NULL\n" + 
				"|--------------------R:\n" + 
				"|-------------------------NULL\n", bst.toString());
	}

	/**
	 * Constructor tests for null inputs and for catching Runtime Exceptions
	 * for second constructor.
	 */
	@Test (expected = RuntimeException.class)
	public void constructorNullInput() {
		BinarySearchTree bst2 = new BinarySearchTree(null,
				null);
		Assert.assertEquals(bst2.toString(), "|NULL\n");
		BinarySearchTree bst3 = new BinarySearchTree("incorrect", 7);
	}

	/**
	 * Test for how methods handle null inputs or root being null.
	 */
	@Test
	public void nullOperationsTester(){
		bst.root = null;
		Assert.assertEquals(null, bst.getSmallestCity());
		Assert.assertEquals(null, bst.getLargestCity());
		Assert.assertEquals(0, bst.getMaxDepth());
		Assert.assertEquals(0, bst.getMaxWidth());
		bst.addCity("A", 5);
		Assert.assertNotNull(bst.root);
	}

	/**
	 * Test for how methods handle null inputs or root being null.
	 */
	@Test
	public void nullTraversalsTester(){
		bst.root = null;
		Assert.assertEquals(null, bst.getPreOrderTraversal());
		Assert.assertEquals(null, bst.getPostOrderTraversal());
		Assert.assertEquals(0, bst.getTwistyTraversal());
		Assert.assertEquals(0, bst.getMaxWidth());
	}

}
