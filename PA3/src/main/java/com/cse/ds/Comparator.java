package com.cse.ds;

/**
 * 
 * @author harsh
 *
 * @param <E>
 */
public interface Comparator<E> {
	
	public boolean comparison(E x, E y, boolean ascending);

}
