package com.cse.ds.comparator;

import com.cse.ds.Comparator;
import org.mockito.internal.matchers.Null;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains StringComparator class that extends Comparator
 */

/**StringComparator class includes comparison method that compares two Strings
 * using lexigraphical sorting and whether they should be in ascending order.*/
public class StringComparator implements Comparator<String> {

	@Override
	public boolean comparison(String x, String y, boolean ascending) throws NullPointerException {

		if(x == null || y == null){
			throw new NullPointerException();
		}

		int value = x.compareTo(y);

		/*A negative value means x comes before y in lexigraphical which is
		 * what we want for ascending.*/
		if (value <= 0 && ascending == true) {
			return true;
		}
		if (value > 0 && ascending == true) {
			return false;
		}

		/*A positive value means x comes after y which is what we want for
		descending because we're going from higher to lower.*/
		if (value >= 0 && ascending == false) {
			return true;
		}
		if (value < 0 && ascending == false) {
			return false;
		}
		return false;
	}

}
