package com.cse.ds.sort;

import com.cse.ds.Comparator;
import com.cse.ds.Sorting;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains SelectionSort class that implements the Sorting class.
 */

/** SelectionSort class contains the Sort class which uses a for loop to find
 * the smallest element in the list, and then continues to repeat the process
 * for the remaining elements until the list is completely sorted..*/
public class SelectionSort<T> implements Sorting<T> {
	
	private Comparator<T> comparator;
	
	public SelectionSort(Comparator<T> comparator) throws NullPointerException{
		if(comparator == null){
			throw new NullPointerException();
		}
		this.comparator = comparator;
	}
	
	@Override
	public void sort(T[] array, boolean ascending) throws NullPointerException {
		if(array == null){
			throw new NullPointerException();
		}

		for(int i = 0; i < array.length - 1; i++){
			//System.out.println(i);
			int minIndex = i;
			for(int j = i + 1; j < array.length; j++){
				//System.out.println(j);
				if((comparator.comparison(array[j], array[minIndex], ascending))){
					//System.out.println(mini);
					minIndex = j;
				}
			}
			T min = array[minIndex];
			array[minIndex] = array[i];
			array[i] = min;

		}
	}

}
