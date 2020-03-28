package com.cse.ds.sort;

import com.cse.ds.Sorting;
import org.mockito.internal.matchers.Null;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains CountSort class that implements the Sorting class.
 * /

 /** CounSort contains a Sort method that uses an order list to sort an area by listing
 * the amount of occurences it has in the correct order the array should add,
 * and then creating a new list with the multiplies of that amount organized
 * properly. */
public class CountSort<T> implements Sorting<T> {
	
	private T[] order;
	
	public CountSort(T[] order) throws NullPointerException{
		if(order == null){
			throw new NullPointerException();
		}
		this.order = order;
	}
	
	@Override
	public void sort(T[] array, boolean ascending) throws NullPointerException{
		if(array == null){
			throw new NullPointerException();
		}
		int count[] = new int [order.length]; //{order.length()};

		for(int i = 0; i < array.length; i++){
			//System.out.println(array);
			for(int j = 0; j < order.length; j++){
				if(array[i].equals(order[j])){
					count[j] += 1;
				}
			}
		}


		if(ascending == true){
		int index = 0;
		for(int i = 0; i < count.length; i++){
			while(count[i] > 0){
				array[index] = order[i];
				index += 1;
				count[i] -= 1;
			}
		}}

		if(ascending == false){

			int index = array.length - 1;

			for(int i = 0; i < count.length; i++){
				//System.out.println(index);
				//System.out.println(i);
				while(count[i] > 0){
					//System.out.println(index);
					//System.out.println(i);
					//System.out.println(count[i]);
					array[index] = order[i];
					index -= 1;
					count[i] -= 1;
				}

			}
		}

	}
}
