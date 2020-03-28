package com.cse.ds.heaps;

import java.util.Arrays;

/**
 * 
 * @author harsh
 *
 */
public class Heap {
	
	private Tuple[] data; //This stores data in accoradance with heap rules.
	private int last_idx; //This keeps track of last index where an element was inserted.
	
	public Heap() {
		data = new Tuple[1];
		last_idx = 0;
	}
	
	/**
	 * Create a heap out of an array of Tuples.
	 * @param arr
	 */
	public Heap(Tuple arr[]) {
		this.data = arr;
		last_idx =
	}
	
	/**
	 * This corrects a single violation at position i, if left and right 
	 * subtrees are max heaps.
	 * @param i
	 */
	public void heapify(int i) {
		//TODO: implement heapify
	}
	
	/**
	 * This function returns the max element from the heap without removing 
	 * the element from heap.
	 * @return
	 */
	public Tuple getMax() {
		//TODO: Fill implementation
		return null;
	}
	
	/**
	 * This function removes and returns the max element from the heap
	 * @return
	 */
	public Tuple heappop() {
		//TODO: Fill implementation
		return null;
	}
	
	/**
	 * This function increases the priority of a node.
	 * @param i
	 * @param priority
	 */
	public void increasePriority(int i, int priority) {
		//TODO: Fill implementation
	}
	
	/**
	 * This function pushes a new element in the heap
	 * @param arg
	 */
	public void heappush(Tuple arg) {
		//TODO: Fill implementation
	}
	
	/**
	 * This function returns the size of the heap.
	 * @return
	 */
	public int getSize()
	{
		//TODO: Fill implementation
		return 0;
	}
		
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(data);
	}
}
