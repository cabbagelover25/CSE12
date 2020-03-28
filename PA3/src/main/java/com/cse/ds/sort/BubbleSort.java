package com.cse.ds.sort;

import com.cse.ds.Comparator;
import com.cse.ds.Sorting;
import com.cse.ds.comparator.IntegerComparator;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains BubbleSort class that extends Sorting class
 *
 * BubbleSort contains a sort method that compares array items next to each other and swaps
 * accordingly. The loop continues to run until no more swaps are nessesary
 * meaning all items are correctly sorted. */
public class BubbleSort<T> implements Sorting<T> {
	
	private Comparator<T> comparator;
	private boolean swap = true;
	
	public BubbleSort(Comparator<T> comparator) throws NullPointerException{
        if(comparator == null){
            throw new NullPointerException();
        }
	    this.comparator = comparator;
	}
	
	@Override
	public void sort(T[] array, boolean ascending) {

        while(swap == true) {
            swap = false;
            for(int i = 0; i <= array.length - 2; i++){
                //System.out.println(i);
                //System.out.println(array.length);
                if(!(comparator.comparison(array[i], array[i + 1], ascending))){
                    swap = true;
                    T larger = array[i];
                    T smaller = array[i + 1];
                    array[i] = smaller;
                    array[i + 1] = larger;
                }
                //System.out.println(i);
            }

        }

	}

}
