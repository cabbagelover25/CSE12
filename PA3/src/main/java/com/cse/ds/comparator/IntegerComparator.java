package com.cse.ds.comparator;

import com.cse.ds.Comparator;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains IntegerComparator class that extends Comparator
 */

/**IntegerComparator class includes comparison method that compares two Integers
 * by converting them to integers, smallest to largest if ascending, largest
 * to smallest if not.*/
public class IntegerComparator implements Comparator<Integer> {

	@Override
	public boolean comparison(Integer x, Integer y, boolean ascending) throws NullPointerException {
		/* If ascending then y should be greater then or equal to x(5,6,7,8_,
		 return true if so, false otherwise.
		 */
		if(x == null || y == null){
			throw new NullPointerException();
		}

		int x1 = x.intValue();
		int y1 = y.intValue();
		if(x1 == y1){
			return false;
		}
		if (x1 <= y1 && ascending == true) {
			return true;
		}
		if (x1 > y1 && ascending == true) {
			return false;
		}

		/*If descending then the 1st number should be larger(8,7,6,5), return
		 * true if x is greater then or equal to y, false otherwise.*/
		if (x1 >= y1 && ascending == false) {
			return true;
		}
		if (x1 < y1 && ascending == false) {
			return false;
		}
		return false;
	}
}
