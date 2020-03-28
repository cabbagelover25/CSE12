package com.cse.ds.sort;

import com.cse.ds.Comparator;
import com.cse.ds.Sorting;
import org.mockito.internal.matchers.Null;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains MergeSort class that implements SortingClass
 */

/**MergeSort class contains sort method that does a recursive loop of splitting
 * an inputted array repeately until only two elements are left, and then
 * using the specified comparitar to sort them and build the list back up.*/
public class MergeSort<T> implements Sorting<T> {

	private Comparator<T> comparator;
	T[] temp;
	T[] A;
	boolean ascending;

	public MergeSort(Comparator<T> comparator) throws NullPointerException
	{
		if(comparator == null){
			throw new NullPointerException();
		}
		this.comparator = comparator;

	}

	@Override
	public void sort(T[] array, boolean ascending) throws NullPointerException{
		if(array == null){
			throw new NullPointerException();
		}
		this.A = array;
		this.temp = (T[]) new Object[A.length];
		this.ascending = ascending;
		mergeSort(0, array.length - 1);
	}

	private void mergeSort(int low, int high) {

		if(low < high){
			int middle = low + ((high - low) / 2);
			mergeSort(low, middle);
			mergeSort(middle + 1, high);
			merge(low, middle, high);
		}

	}

	private void merge(int low, int middle, int high){
		for(int i = low; i<= high; i++) {
			temp[i] = A[i];
		}
			int i = low;
			int j = middle + 1;
			int k = low;
		while(i <= middle && j <= high) {
			if (comparator.comparison(temp[i], temp[j], ascending)) {
				A[k] = temp[i];
				i++;
			} else{
				A[k] = temp[j];
				j++;
			}
			k++;
		}
		while(i <= middle){
			A[k] = temp[i];
			k++;
			i++;
		}
		}
	}


