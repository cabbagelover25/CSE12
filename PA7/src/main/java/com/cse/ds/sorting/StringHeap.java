package com.cse.ds.sorting;

import java.util.Arrays;

/**
 * 
 * @author harsh
 *
 */
public class StringHeap {
	
	private String[] data;
	private int last_idx;
	
	public StringHeap() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create a heap out of an array of Strings.
	 * @param arr
	 */
	public StringHeap(String arr[]) {
		// TODO: Implementation here
	}
	
	/**
	 * This corrects a single violation at position i, if left and right 
	 * subtrees are max heaps.
	 * @param i
	 */
	public void heapify(int i) {
		// TODO: Implementation here
	}
	
	/**
	 * This function returns the max element from the heap without removing 
	 * the element from heap.
	 * @return
	 */
	public String getMax() {
		// TODO: Implementation here
		return null;
	}
	
	/**
	 * This function removes and returns the max element from the heap
	 * @return
	 */
	public String heappop() {
		// TODO: Implementation here
		return null;
	}
	
	/**
	 * This function returns the size of the heap.
	 * @return
	 */
	public int getSize()
	{
		// TODO: Implementation here
		return 0;
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(data);
	}
}
