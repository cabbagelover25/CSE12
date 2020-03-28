package com.cse.ds.comparator;

import com.cse.ds.Comparator;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains ColorComparator class that extends Comparator
 */

/**ColorComparator class includes comparison method that compares two colors
 * that are either red, blue, or white. The return value largely depends
 * on the ascending boolean.*/
public class ColorComparator implements Comparator<String> {

	@Override
	public boolean comparison(String x, String y, boolean ascending) throws NullPointerException{
		if(x == null || y == null){
			throw new NullPointerException();
		}
		x = x.toLowerCase();
		y = y.toLowerCase();
		if(x.equals(y)){
			return false;
		}
		String[] colorArray = new String[3];
		colorArray[0] = "blue";
		colorArray[1] = "white";
		colorArray[2] = "red";
		int xInt = 0;
		int yInt = 0;
		// For loop to see what values x and y correspond to
		for(int i = 0; i < colorArray.length; i++) {
			if(colorArray[i].equals(x)){
				xInt = i;
			}
			if(colorArray[i].equals(y)){
				yInt = i;
			}
		}

		/*If ascending is true, then y is supposed to have a higher
		* index then x bc that would mean that it comes later in the list.
		* If tie then true. If less then false.*/
		if(xInt <= yInt && ascending == true){
			return true;
		}
		if(xInt > yInt && ascending == true){
			return false;
		}

		/* If ascending is false, then x should have a lower index then y
		* because that would mean that x comes after y since it has a larger
		* index.
		* */
		if(xInt >= yInt && ascending == false){
			return true;
		}
		if(xInt < yInt && ascending == false){
			return false;
		}
		return false;
	}


}
