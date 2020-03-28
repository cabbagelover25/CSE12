/**
 * Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: May 31, 2019
 *
 * Contains BinarySearchTree class which implements a basic Binary Search Tree
 * data structure. A Binary Search Tree is made up of nodes which draw from the
 * Node.java class.
 */
package com.cse.ds;

import org.mockito.internal.matchers.Null;

import java.util.List;
import java.lang.Math;
import java.util.*;
import java.util.Scanner;
import java.io.*;

/**
 * The BinarySearchTree class allows for the creation of Balanced Trees through
 * two constructors. Common operations like adding cities, or getting the max
 * width or depth of a tree. Traversals through the tree, viewing the tree
 * from different perspectives, and creating a toString representation of the
 * BST tree.
 */
public class BinarySearchTree {

	//============== GLOBAL VARIABLES CREATION ====================//
	Node root;
	List<String> preList = new ArrayList<String>();
	List<String> postList = new ArrayList<String>();
	List<String> positiveList = new ArrayList<String>();
	List<String> negativeList = new ArrayList<String>();
	List<Integer> positiveLevels = new ArrayList<Integer>(
	);
	List<Integer> negativeLevels = new ArrayList<Integer>();
	List<List<String>> totalList = new ArrayList<>();
	List<Node> orderedList = new ArrayList<>();
	String item;
	int maxWidth = 0;
	String side = "L:";
	int runningCount;

//============== BALANCED TREE CREATION ====================//
	/**
	 * Initializes a balanced binary search tree. Each index in cities array
	 * corresponds to the same index in population array.
	 * @param cities 		Array of city names
	 * @param population 	Array of city populations
	 */
	public BinarySearchTree(String[] cities, int[] population) {
		//Return if cities or population are empty or null.

		if (cities == null || population == null) {
			root = null;
			return;}
		if (cities.length == 0 || population.length == 0) {
			root = null;
			return; }

		// Find mid of left and right
		int left = 0;
		int right = cities.length - 1;
		int mid = (left + right) / 2;

		//Use recursive method sorter to loop through array and add left and
		// right items on all nodes.
		Node N = new Node(cities[mid], population[mid]);
		N.left = sorter(left, mid - 1, cities, population);
		N.right = sorter(mid + 1, right, cities, population);
		root = N;
	}

	/**
	 * Helper method for BinarySearchTree that recursively sorts the Nodes based
	 * on their position in the array.
	 * @param left       Index of array representing left side of recursion
	 * @param right      Index of array representing right side of recursion
	 * @param cities     Array of city names
	 * @param population Array of city populations
	 * @return
	 */
	private Node sorter(int left, int right, String[] cities, int[] population) {
		if (left >= right) {
			if (left == right) {
				return new Node(cities[left], population[left]);
			}
			return null;
		}
		int mid = (left + right) / 2;
		Node M = new Node(cities[mid], population[mid]);
		M.left = sorter(left, mid - 1, cities, population);
		M.right = sorter(mid + 1, right, cities, population);

		return M;
	}

	/**
	 * Initializes a balanced binary search tree. Reads in a file whose filename
	 * is passed as an argument and the number of lines that the file contains.
	 * @param filename 	String of name of file
	 * @param numlines 	int of number of lines in file
	 */
	public BinarySearchTree(String filename, int numlines) {
		//Attempt to create file, returns if FileNotFound.
		Scanner fromFile;
		try { fromFile = new Scanner(new File(filename)); }
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		// Adds item to city and population arrays for each line in file
		ArrayList<Node> newList = new ArrayList<>();
		int[] pop = new int[numlines];
		String[] names = new String[numlines];
		for (int i = 0; i < numlines; i++) {
			String nextLine = fromFile.nextLine();
			String cityTemp = nextLine.substring(0, nextLine.indexOf(61));
			String populationTemp = nextLine.substring(nextLine.indexOf(62) + 1,
					nextLine.length());
			pop[i] = (Integer.parseInt(populationTemp.trim()));
			names[i] = cityTemp.trim();
		}

		//Reverses the order of both lists since file was organized backwards
		String[] namesReversed = new String[numlines];
		int[] popReversed = new int[numlines];
		int j = 0;
		for (int i = pop.length - 1; i >= 0; i--) {
			namesReversed[j] = names[i];
			popReversed[j] = pop[i];
			j++;
		}

		//Duplicates work done in first constructor, now with the items in
		// proper order.
		int left = 0;
		int right = namesReversed.length - 1;
		int mid = left + (right / 2);
		Node N = new Node(namesReversed[mid], popReversed[mid]);
		this.root = N;
		N.left = sorter(left, mid - 1, namesReversed, popReversed);
		N.right = sorter(mid + 1, right, namesReversed, popReversed);
	}

	//============== COMMON TREE OPERATIONS ====================//

	/**
	 * Insert a new node with city and population set to the values sent as the
	 * arguments into the BST such that the properties of the BST still hold
	 * true.
	 * @param city 			String name of city to input
	 * @param population 	Int of city's population
	 */
	public void addCity(String city, int population) {
		Node insert = new Node(city, population);
		root = add(root, insert);
	}

	/**
	 * Helper method for addCity that recursively goes through the Nodes to
	 * insert the city into the correct location.
	 * @param root 		Node at root of BST
	 * @param insert 	Node to insert in BST
	 * @return			Node of new root
	 */
	private Node add(Node root, Node insert) {
		if (root == null) {
			root = insert;
			return root;
		}
		if (insert.population <= root.population) {
			root.left = add(root.left, insert);
		} else if (insert.population > root.population) {
			root.right = add(root.right, insert);
		}
		return root;
	}


	/**
	 * Returns the maximum depth of the binary search tree. Root is at depth 1.
	 * @return int of maximum depth
	 */
	public int getMaxDepth() {
		if(root == null){
			return 0;
		}
		ArrayList<Node> list = new ArrayList<Node>();
		list.add(root);
		return down(1, list);
	}

	/**
	 * Helper method for getMaxDepth, recursively finds child Nodes of Nodes
	 * on current level until their are no more child Nodes which specifies
	 * the maximum found depth.
	 * @param level
	 * @param list
	 * @return int of maximum depth
	 */
	private int down(int level, ArrayList<Node> list) {
		ArrayList<Node> lowerList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if ((list.get(i).left) != null) {
				lowerList.add(list.get(i).left);
			}
			if ((list.get(i).right) != null) {
				lowerList.add(list.get(i).right);
			}
		}
		if (lowerList.size() == 0) {
			return level;
		}
		return down(level + 1, lowerList);
	}

	/**
	 * Returns the maximum width of the tree
	 * @return int of max width
	 */
	public int getMaxWidth() {
		if(root == null){
			return 0;
		}
		maxWidth = 0;
		positiveLevels.clear();
		negativeLevels.clear();
		preList = getSortedCities();
		positiveList.clear(); //Negative
		negativeList.clear(); //Positive
		postList.clear();// = new ArrayList<>();
		widthHelper(root, 0, 1);

		for(int i = negativeList.size() - 1; i >= 0; i--){
			postList.add(negativeList.get(i));
		}
		for(int j = 1; j < positiveList.size(); j++) {
			postList.add(positiveList.get(j));
		}
		return postList.size() - 1;
	}

	public void widthHelper(Node current, int width, int level) {
		if(current == null){
			return;
		}
		if(width >= 0){
			if(width >= positiveList.size()){
				System.out.println(current.city);
				positiveList.add(current.city);
				positiveLevels.add(level);
				//System.out.println("TEST");
			}
			else{
				if(positiveLevels.get(width) > level){
					positiveList.set(width, current.city);
				}
			}
		}
		if(width <= 0){
			int widthAbs = Math.abs(width);
			if(widthAbs >= negativeList.size()){
				negativeList.add(current.city);
				negativeLevels.add(level);
			}
			else{
				if(negativeLevels.get(widthAbs) > level){
					negativeList.set(widthAbs, current.city);
				}
			}
		}
		widthHelper(current.left, width - 1, level + 1);
		widthHelper(current.right, width + 1, level + 1);
	}

	/**
	 * Returns the name of the smallest city in the BST. In this BST, smallest
	 * city corresponds to the city having the least population.
	 * @return String of city with smallest population.
	 */
	public String getSmallestCity() {
		if (root == null) {
			return null;
		}
		Node current = root;
		while (current.left != null) {
			current = current.left;
		}
		return current.city;
	}

	/**
	 * Returns the name of the largest city in the BST. In this BST, largest
	 * city corresponds to the most populated city.
	 * @return String of city with largest population.
	 */
	public String getLargestCity() {
		if (root == null) {
			return null;
		}
		Node current = root;
		while (current.right != null) {
			current = current.right;
		}
		return current.city;
	}
	
	//============== TREE TRAVERSALS ====================//

	/**
	 * Returns a list of city names in the order of a pre-order traversal on the
	 * current binary search tree.
	 * @return List of organized city names following a pre-order traversal.
	 */
	public List<String> getPreOrderTraversal() {
		if(root == null){
			return null;
		}
		preList.clear();
		//List<String> preList = new ArrayList<String>();
		preList.add(root.city);
		below(root.left);
		below(root.right);
		return preList;
	}

	/**
	 * Helper method for getPreOrderTraversal that recursively
	 * goes through the nodes adding to the List that is returned.
	 * Recursively goes through by node, left, and then right.
	 * @param current Node to add to List
	 */
	private void below(Node current){
		if(current == null){
			return;
		}
		preList.add(current.city);
		below(current.left);
		below(current.right);
		return;
	}

	/**
	 * Returns a list of city names in the order of a post-order traversal on
	 * the current binary search tree.
	 * @return List of organized city names following a post-order traversal.
	 */
	public List<String> getPostOrderTraversal() {
		//Returns null if no list exists.
		if(root == null){ return null; }

		postList.add(root.city);
		below2(root.right);
		below2(root.left);
		List<String> reverseList = new ArrayList<String>();
		int j = 0;
		for(int i = postList.size() - 1; i >= 0; i--) {
			reverseList.add(j, postList.get(i));
			j++; }
		return reverseList;
	}

	/**
	 * Helper method for getPostOderTraversal. Recursively goes through adding
	 * Nodes to the List in the order of root, right, and left.
	 * @param current Node to add to list.
	 */
	private void below2(Node current){
		if(current == null){
			return;
		}
		postList.add(current.city);
		below2(current.right);
		below2(current.left);
		return;
	}


	/**
	 * Returns the list of city names in sorted order.
	 * @return List of city names in ascending order.
	 */
	public List<String> getSortedCities() {
		if(root == null){
			return null;
		}
		preList.clear();
		below3(root.left);
		preList.add(root.city);
		below3(root.right);
		return preList;
	}

	/**
	 * Helper method for getSortedCities, recursively adds Nodes to List
	 * by going through left side, then root, then the right side.
	 * @param current Node to add to list.
	 */
	private void below3(Node current){
		if(current == null){
			return;
		}
		below3(current.left);
		preList.add(current.city);
		below3(current.right);
		return;
	}

	/**
	 * Returns a list of lists. Each list within the list stores all of the city
	 * names at a specific level of the binary search tree. Return list where
	 * levels closer to the root appear first.
	 * @return List of lists of city names
	 */
	public List<List<String>> getLevelWiseCities() {
		if(root == null){
			return null;
		}
		totalList.clear();
		List<String> topLevel = new ArrayList();
		List<Node> topNode = new ArrayList<>();
		topLevel.add(root.city);
		topNode.add(root);
		totalList.add(topLevel);
		LevelWiseRecursive(1, topNode);
		return totalList;
	}

	/**
	 * Helper method for getLevelWiseCities that recursively goes through
	 * nodes and adds to the appropriate spot in the list.
	 * @param level Int of current depth in BST
	 */
	public void LevelWiseRecursive(int level, List<Node> list){
		ArrayList<Node> lowerList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if ((list.get(i).left) != null) {
				lowerList.add(list.get(i).left);
			}
			if ((list.get(i).right) != null) {
				lowerList.add(list.get(i).right);
			}
		}
		if (lowerList.size() == 0) {
			return;
		}
		List<String> currentLevel = new ArrayList<>();
		for(int i = 0; i < lowerList.size(); i++){
			currentLevel.add(lowerList.get(i).city);
		}
		totalList.add(currentLevel);
		LevelWiseRecursive(level + 1, lowerList);
		return;
	}

	/**
	 * Returns the list of lists of cities at each level when performing a
	 * twisty traversal on the tree
	 * @return List<List<String>> List of list of cities
	 */
	public List<List<String>> getTwistyTraversal() {
		totalList.clear();
		totalList = getLevelWiseCities();
		int i = 1;
		while(i< totalList.size()){
			preList = new ArrayList<>();
			preList = totalList.get(i);
			postList = new ArrayList<>();
			int k = 0;
			for(int j = preList.size() -1; j >= 0; j--){
				System.out.println(postList);
				System.out.println(preList);
				System.out.println("I: " + i + " J: " + j + " K: " + k);
				postList.add(preList.get(j));
				k++;
			}
			totalList.set(i, postList);
			i += 2;
		}

		return totalList;
	}
	
	//============== TREE VIEWS ====================//

	/**
	 *Returns the right view of the BST
	 * @return List of cities from right view of tree
	 */

	public List<String> getRightView() {
		preList.clear();
		if(root == null){
			return null;
		}
		preList.add(root.city);
		Node current = root;
		while(current.right != null){
			current = current.right;
			preList.add(current.city);
		}
		if(current.left != null){
			preList.add(current.left.city);
		}
		return preList;

    }

	/**
	 * Returns the left view of the BST
	 * @return
	 */
	public List<String> getLeftView() {
		preList.clear();
		if(root == null){
			return null;
		}
		preList.add(root.city);
		Node current = root;
		while(current.left != null){
			current = current.left;
			preList.add(current.city);
		}
		if(current.right != null){
			preList.add(current.right.city);
		}
		return preList;

	}

	/**
	 * Returns the top view of the BST from left to right
	 * @return
	 */
	public List<String> getTopView(){
		positiveLevels.clear();
		negativeLevels.clear();
		System.out.println(toString());
		preList = getSortedCities();
		positiveList.clear(); //Negative
		negativeList.clear(); //Positive
		postList.clear();// = new ArrayList<>();
		helpTop(root, 0, 1);


		for(int i = negativeList.size() - 1; i >= 0; i--){
			postList.add(negativeList.get(i));
		}
		for(int j = 1; j < positiveList.size(); j++) {
			postList.add(positiveList.get(j));
		}

		return postList;
	}

	/**
	 * Helper method for getTopView that uses recursion to sort
	 * through Nodes.
	 * @param current Current Node in recursion
	 * @param width Current width location of Node
	 * @param level Current depth of tree
	 */
	private void helpTop(Node current, int width, int level){
		System.out.println("HELLO");
		if(current == null){
			return;
		}
		if(width >= 0){
			if(width >= positiveList.size()){
				System.out.println(current.city);
				positiveList.add(current.city);
				positiveLevels.add(level);
				//System.out.println("TEST");
			}
			else{
				if(positiveLevels.get(width) > level){
					positiveList.set(width, current.city);
				}
				System.out.println("WIDTH " + width);
				System.out.println("Positive list" + positiveList.size());
				System.out.println(current.city);
				//positiveList.set(width, current.city);
			}
		}
		if(width <= 0){
			int widthAbs = Math.abs(width);
			if(widthAbs >= negativeList.size()){
				//System.out.println
				System.out.println(negativeList);
				System.out.println(current.city);
				System.out.println("Level: " + level + " /Width: " + width);
				negativeList.add(current.city);
				negativeLevels.add(level);
			}
			else{
				if(negativeLevels.get(widthAbs) > level){
					negativeList.set(widthAbs, current.city);
				}
				//negativeList.set(widthAbs, current.city);
			}
		}
		helpTop(current.left, width - 1, level + 1);
		helpTop(current.right, width + 1, level + 1);
	}

	/**
	 * Returns the bottom view of the BST from left to right
	 * @return
	 */
	public List<String> getBottomView() {
		positiveLevels.clear();
		negativeLevels.clear();
		preList = getSortedCities();
		positiveList= new ArrayList<>(); //Negative
		negativeList = new ArrayList<>(); //Positive
		postList = new ArrayList<>();
		postList.clear();
		helpBottom(root, 0, 1);
		for(int i = negativeList.size() - 1; i >= 0; i--){
			postList.add(negativeList.get(i));
		}
		for(int i = 1; i < positiveList.size(); i++) {
			postList.add(positiveList.get(i));
		}

        return postList;
	}

	/**
	 * Helper method for bottomView that uses recursion to sort
	 * through Nodes.
	 * @param current Current Node in recursion
	 * @param width Current width location of Node
	 * @param level Current depth of tree
	 */
	private void helpBottom(Node current, int width, int level){
		if(current == null){
			return;
		}
		if(width >= 0) {
			if (width >= positiveList.size()) {
				positiveList.add(current.city);
				positiveLevels.add(level);
			} else {
				if (positiveLevels.get(width) < level) {
					positiveList.set(width, current.city);
				}
				}
			}
			if (width <= 0) {
				int widthAbs = Math.abs(width);
				if (widthAbs >= negativeList.size()) {
					negativeList.add(current.city);
					negativeLevels.add(level);
				} else {
					if (negativeLevels.get(widthAbs) < level) {
						negativeList.set(widthAbs, current.city);
					}
				}
			}
			helpBottom(current.left, width - 1, level + 1);
			helpBottom(current.right, width + 1, level + 1);
		}
	
	//============== SPECIAL TREE OPERATIONS ====================//

	/**
	 * Returns the tilt of the whole tree starting at the root.The tilt of a
	 * tree node is defined as the absolute difference between the sum of all
	 * left subtree node values and the sum of all right subtree node values
	 * @return int of Tilt of tree
	 */
	public int getBSTilt() {
		if(root == null){
			return 0;
		}
		Node current = root;
		runningCount = 0;
		Tilt(root);
		return runningCount;
	}

	/**
	 * Helper method for getBSTilt that goes through BST recursively
	 * @param current Current Node
	 * @return int of tilt to add
	 */
	private int Tilt(Node current){
		if(current == null){
			return 0;
		}
		int currentResult = current.population;
		int rightResult = Tilt(current.right);
		int leftResult = Tilt(current.left);
		runningCount += Math.abs(rightResult - leftResult);
		return rightResult + leftResult + currentResult;
	}

	/**
	 * Returns the list of city names - routes that one can take from root node
	 * to each leaf node.
	 * @return List of city name routes
	 */
	public List<String> getAllPaths() {
		if(root == null){
			return null;
		}
		preList.clear();
		String path = "=>" + root.city;
		pathHelper(root.left, path);
		pathHelper(root.right, path);
		return preList;
	}

	/**
	 * Helper method for getAllPaths
	 * @param current Current Node
	 * @param currentString Current String path
	 */
	public void pathHelper(Node current, String currentString){
		if(current == null){
			return;
		}
		currentString = currentString + "=>" + current.city;
		if(current.left == null && current.right == null){
			preList.add(currentString);
			return;
		}

		pathHelper(current.left, currentString);
		pathHelper(current.right, currentString);
	}

	/**
	 * Flatten the tree as shown above to get a structure like singly linked
	 * list.
	 */
	public void flattenToLL() {
		List<Node> ordered = sortBST();
		Node current = root;
		root.left = null;
		for(int i = 1; i < ordered.size(); i++){
			current.right = ordered.get(i);
			current = current.right;
			current.left = null;
		}
	}

	/**
	 * Helper method for flattenToLL
	 * @return Child Node
	 */
	public List<Node> sortBST() {
		if(root == null){
			return null;
		}
		orderedList.clear();
		orderedList.add(root);
		below4(root.left);
		below4(root.right);
		return orderedList;
	}

	/**
	 * Helper method for getPreOrderTraversal that recursively
	 * goes through the nodes adding to the List that is returned.
	 * Recursively goes through by node, left, and then right.
	 * @param current Node to add to List
	 */
	private void below4(Node current){
		if(current == null){
			return;
		}
		orderedList.add(current);
		below4(current.left);
		below4(current.right);
		return;
	}
	
	//============== TREE VISUALIZATION ====================//

	/**
	 * Returns a string which represents the entire binary search tree
	 * @return String of BST Tree
	 */
	@Override
	public String toString() {
		item = "";
		if(root == null){
			return "|NULL" + "\n";
		}
		item += ("|" + root.city + ":" + root.population + "\n");
		side = "|L:" + "\n";
		item += (side);
		toStringBelow(root.left, 1);
		side = "|R:" + "\n";
		item += side;
		toStringBelow(root.right,1);
		return item;
	}

	/**
	 * Helper method for toString that recursively goes through BST Tree.
	 * @param current Current Node
	 * @param level Current depth of Node
	 */
	private void toStringBelow(Node current,  int level) {
		item += "|";
		for(int i = 0; i < level; i++){
			item += "-----";
		}
		if((current == null)){
			item += ("NULL" + "\n");
			return;
		}
		item += (current.city + ":" + current.population + "\n");
			//System.out.println("LEFT");
			item += "|";
			for (int i = 0; i < level; i++) {
				item += "-----";
			}
			side = "L:" + "\n";
			item += side;
		toStringBelow(current.left, level + 1);

			//System.out.println("RIGHT");
			item += "|";
			for (int i = 0; i < level; i++) {
				item += "-----";
			}
			side = "R:" + "\n";
			item += side;
		toStringBelow(current.right, level + 1);
	}


}