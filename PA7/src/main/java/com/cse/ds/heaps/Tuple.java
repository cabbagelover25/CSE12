package com.cse.ds.heaps;

/**
 * 
 * @author harsh
 *
 * @param <E>
 */
public class Tuple<E> {

	public final int priority;
	public final E value;

	public Tuple(int priority, E value) {
		this.priority = priority;
		this.value = value;
	}
	
	@Override
	public String toString() {
		if(null != value)
			return "("+priority+","+value.toString()+")";
		else
			return "("+priority+","+value+")";
	}
}
