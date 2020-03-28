package com.cse.ds;

import java.util.*;

public class MyQueue<E> {

    private MyQueueNode<E> head;
    private MyQueueNode<E> tail;
    private int nElements;

    /** Constructor implementing myQueue object of generic
     * type E elements.
     */
    public MyQueue() {
        
        this.head = new MyQueueNode<E>(null);
        this.tail = new MyQueueNode<E>(null);
        nElements = 0;

    }

    /**Enqueue adds takes in an element and throws a NullPointerException if it's null,
     * @param: Element to add into queue
     * */
    public void enqueue(E element) throws NullPointerException {

        // Throw NullPointer if element is null
        if(element == null){
            throw new NullPointerException();
        }
        /** Loop through all the elements until you get to the last one, could
        also be done using a while loop and specifying to stop when getNext
         equals null */
        MyQueueNode lastNode = head;
        for(int i = 0; i < nElements; i++){
            lastNode = lastNode.getNext();
        }

        /** Create new node with element, setNext on the last element in the
        queue, and update list by one */
        MyQueueNode<E> newNode = new MyQueueNode<E>(element);
        lastNode.setNext(newNode);
        nElements += 1;
    }

    /** Remove element from the front of the queue, throw NoSuchElemetnt
     * if queue is empty
     * @return: Removed element at front of queue
     * */
    public E dequeue() throws NoSuchElementException {
        //If queue is empty, throw exception
        if(nElements == 0){
            throw new NoSuchElementException();
        }

        /** Get the first element of the queue to remove, and then
         * connect the head element with the second element in the
         * queue, thus entirely taking out the removedElement and returning
         * it's element */
        nElements -= 1;
        MyQueueNode<E> removedNode = head.getNext();
        MyQueueNode<E> newFirst = removedNode.getNext();
        head.setNext(newFirst);
    	return removedNode.getElement();


    }

    /**Returns the element of generic type E that is at the top
     * of the list*/
    public E peek() throws NoSuchElementException {
        // Throw NoSuchElementException if empty
        if (nElements == 0) {
            throw new NoSuchElementException();
        }
        return head.getNext().getElement();
    }
    /** Checks if queue is empty, otherwise returns false..
     * @return: Boolean on whether list is empty or not*/
    public boolean isEmpty() {
        
        if(nElements == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**Returns size of queue
     * @reutnr: Size of queue*/
    public int size() {
        return nElements;
    }

}
