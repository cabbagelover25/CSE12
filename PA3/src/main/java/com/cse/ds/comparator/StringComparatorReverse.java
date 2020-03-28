package com.cse.ds.comparator;

import com.cse.ds.Comparator;
import org.mockito.internal.matchers.Null;
/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains StudentComparatorRevers class that extends Comparator
 */

/** * A custom comparator for comparing String in reverse order.
 * Ex: ABBZ > ZBA */
public class StringComparatorReverse implements Comparator<String> {

	@Override
	public boolean comparison(String x1, String y1, boolean ascending) throws NullPointerException{

		if(x1.equals(y1)){
			return false;
		}
		if(x1 == null || y1 == null){
			throw new NullPointerException();
		}
		//Convert x1 and y1 to StringBuilder to reverse them.
		StringBuilder x = new StringBuilder();
		x.append(x1);
		x.reverse();

		StringBuilder y = new StringBuilder();
		y.append(y1);
		y.reverse();

		//Apply comparison function on x and y by converting back to toString()
		StringComparator comp = new StringComparator();
		return comp.comparison(x.toString(), y.toString(), ascending);
	}
}
