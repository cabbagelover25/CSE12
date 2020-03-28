/**
 * Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: May 31, 2019
 *
 * Contains Node class that serve as individual components of BST Tree used in
 * the BinarySearchTree class.
 */
package com.cse.ds;

/**
 * Each Node in the Node class stores a city, population, and two child nodes.
 * The class enables the initialization of a node with either an empty
 * constructor or inputted city and population.
 */
public class Node {
    String city;
    int population;
    
    Node left;
    Node right;

    /**
     * Initializes empty Node constructor.
     */
    public Node() {
        this.city = null;
        this.population = 0;
        left = null;
        right = null;
	}

    /**
     * Initializes a Node object that stores the name of the city and the
     * population along with left and right pointers.
     * @param city String of city being created
     * @param population int of population of city
     */
    public Node(String city, int population) {
		this.city = city;
		this.population = population;
		this.left = null;
		this.right = null;
	}

}