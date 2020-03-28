package com.cse.ds;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import com.cse.ds.comparator.ColorComparator;
import com.cse.ds.comparator.StringComparator;
import com.cse.ds.comparator.StudentComparator;
import com.cse.ds.models.Student;
import com.cse.ds.sort.MergeSort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cse.ds.comparator.IntegerComparator;
import com.cse.ds.sort.BubbleSort;
import com.cse.ds.sort.CountSort;
import com.cse.ds.sort.SelectionSort;

/**
 * 
 * @author harsh
 *
 */
public class GenericTest {
	
	static BubbleSort<Integer> bubbleSortInteger;
	static SelectionSort<Integer> selectionSortInteger;
	static CountSort<Integer> countSortInteger;
	static MergeSort<Integer> mergeSortInteger;

	static IntegerComparator intComp;
	static StringComparator strComp;
	static ColorComparator colorComp;
	static StudentComparator gpaComp;
	static Student stud1 = null;
	static Student stud2 = null;
	
	Integer arr1[], arr1r[], arr3[], arr3r[];// = new Integer[10];
	Integer arr2[], arr2r[], arr7[];
	
	@BeforeClass
    public static void setUpBeforeClass() {
		Integer order[] = {0,1,2};

		bubbleSortInteger = new BubbleSort<>(new IntegerComparator());
		selectionSortInteger = new SelectionSort<>(new IntegerComparator());
		countSortInteger = new CountSort<>(order);
		mergeSortInteger = new MergeSort<>( new IntegerComparator());

		intComp = new IntegerComparator();
		strComp = new StringComparator();
		colorComp = new ColorComparator();
		gpaComp = new StudentComparator();

		//Random rd = new Random();




    }
	
    @Before
    public void populatePlayList(){
    	//arr1 = new Integer[]{5,6,7,3,2,4,5,5,5,3,2,6,7,8,10};
		arr1 = new Integer[]{1,2,0,1,0,2};
		//arr1r = new Integer[]{2,1, 8, 2, 6, 4, 7, 2, 1, 10, 2,2, 4, 9, 1, 2, 3, 6, 3, 6, 8, 3,3,4,5,5,5,5,6,6,7,7,8,10,
				//2,2,3,3,4,5,5,5,5,6,6,7,7,8,10, 5,6,7,3,2,4,5,5,5,3,2,6,
				//7,8,10, 4, 4, 4, 2, 6, 2,2,1,1,0,0,4,3,8,9};
		arr1r = new Integer[]{2,2,1,1,0,0};
		arr3 = new Integer[]{4,3,8,9};
		arr3r = new Integer[]{3,4,8,9};



    }

    @Test
	public void bensABitch(){

		//Integer arr[] = new Integer[]{5,4,8, 9, 2};
		//MergeSort<Integer> sorting;
		Random rd = new Random();
		//arr1r = new Integer[500]; //{2,2,3,3,4,5,5,5,5,6,6,7,7,8,10,
		//	2,2,3,3,4,5,5,5,5,6,6,7,7,8,10, 5,6,7,3,2,4,5, 2, 3, 7, 4, 5, 2, 3,4,5,6, 5,5,3,2,6,
		//	7,8,10, 4, 4, 4, 2, 6, 2,2,1,1,0,0,4,3,8,9, 7, 3, 2, 3, 8, 5,7,3,4,5};
		for(int i = 0; i < arr1r.length; i++){
			arr1r[i] = rd.nextInt();
		}
		long start = System.currentTimeMillis();
		countSortInteger.sort(arr1r,true);
		long end = System.currentTimeMillis();
		long time_taken = end-start;
		System.out.println("Sorting time :" + time_taken + "ms");
	}

    @Test
	public void testInteger(){
		int a1 = 7;
		int a2 = 8;
		boolean b = intComp.comparison(a1, a2, false);
		Assert.assertFalse(b);
	}

	@Test
	public void testString(){
		String dog = "dog";
		String cat = "cat";
		boolean b = strComp.comparison(dog, cat, true);
		Assert.assertFalse(b);
	}

	@Test
	public void testColor(){
		String red = "red";
		String blue = "blue";
		boolean b = colorComp.comparison(red, blue, false);
		Assert.assertTrue(b);
	}

	@Test
	public void testStudent(){
		stud1 = new Student("1", 3.0);
		stud2 = new Student("2", 5.0);
		boolean b = gpaComp.comparison(stud2, stud1, false );
		Assert.assertTrue(b);
	}


	@Test
    public void testSelection()
    {
    	selectionSortInteger.sort(arr1, true);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }

	@Test
	public void testBubbleMine()
	{
		bubbleSortInteger.sort(arr3, true);
		System.out.println(Arrays.toString(arr3));
		Assert.assertArrayEquals(arr3r, arr3);
	}

    @Test
    public void testBubble()
    {
    	bubbleSortInteger.sort(arr1, true);
    	System.out.println(Arrays.toString(arr1));
    	System.out.println(Arrays.toString(arr1r));
    	Assert.assertArrayEquals(arr1r, arr1);
    }

	@Test
	public void testMerge()
	{
		mergeSortInteger.sort(arr1, true);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr1r));
		Assert.assertArrayEquals(arr1r, arr1);
	}
    
    @Test
    public void testCount()
    {
    	countSortInteger.sort(arr2, false);
    	System.out.println(Arrays.toString(arr2));
    	System.out.println(Arrays.toString(arr2r));
    	Assert.assertArrayEquals(arr2r, arr2);
    }
}
