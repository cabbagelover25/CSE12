package com.cse.ds.comparator;

import com.cse.ds.Comparator;
import com.cse.ds.models.Student;

/**
 * @author Sam Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 24, 2019
 *
 * Contains StudentComparator class that extends Comparator
 */

/**StudentComparator class includes comparison method that compares two Students
 * depending on their GPA, and if their GPA is equal then their PID..*/
public class StudentComparator implements Comparator<Student> {

	@Override
	public boolean comparison(Student x, Student y, boolean ascending) throws NullPointerException {
		if(x == null || y == null){
			throw new NullPointerException();
		}

		if(x.equals(y)){
			return false;
		}

		Double xGPA = x.getCGPA();
		Double yGPA = y.getCGPA();

		/* If ascending then y should be greater then or equal to x(5,6,7,8_,
		 return true if so, false otherwise.
		 */
		if (xGPA <= yGPA && ascending == true) {
			return true;
		}
		if (xGPA > yGPA && ascending == true) {
			return false;
		}

		/*If descending then the 1st number should be larger(8,7,6,5), return
		 * true if x is greater then or equal to y, false otherwise.*/
		if (xGPA >= yGPA && ascending == false) {
			return true;
		}
		if (xGPA < yGPA && ascending == false) {
			return false;
		}
		return false;
	}

}
