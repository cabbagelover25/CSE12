/** Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: May 1, 2019
 *
 * Contains Stack class that implements a Stack data structure which follows a
 * Last in, First Out strategy.
 **/
//import sun.invoke.empty.Empty;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**The Stack class allows for the implementation of the Stack data structure
 * who's main operations are push, pop, and isEmpty. loadFactor and shrinkFactor
 * allow for the expansion of a Stack's capacity, arr is the Stack itself, and
 * top is the size of the Stack.*/
class Stack<E>
{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of elements: ");
        int amount = scan.nextInt();
        int sum = 0;
        for(int i = 0; i< amount; i++){
            System.out.println("Next");
            sum += scan.nextInt();
        }
        System.out.println(sum);
    }
    float loadFactor;
    float shrinkFactor;
    int top; // Index of the top element
    E arr[];
    int capacity;

    /** Stack constructor without loadFactor or shrinkFactor, thus default
     * values of 1 and 0, respectively, are used.
     * @param: Capacity of stack
     * */
    Stack(int capacity)
    {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
        this.top = 0;
        this.loadFactor = 1;
        this.shrinkFactor = 0;
    }

    /** Stack constructor with loadFactor, but without shrinkFactor, thus
     * default value of 0 is used, which is set automatically.
     * @param: Capacity of stack
     * @param: Float loadFactor to set as variable
     * */
    Stack(int capacity, float loadFactor)
    {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
        this.loadFactor = loadFactor;
        this.top = 0;
    }

    /** Stack constructor with both loadFactor and shrinkFactor specified.
     * @param: Capacity of stack
     * @param: Float loadFactor to set as variable
     * @param: Float shrinkFactor to set as variable
     * */
    Stack(int capacity, float loadFactor, float shrinkFactor)
    {
        this.capacity = capacity;
        arr = (E[]) new Object[capacity];
        this.loadFactor = loadFactor;
        this.shrinkFactor = shrinkFactor;
        this.top = 0;
    }

    /** Checks if list is empty by referencing top of stack.
     * @return: Boolean of whether empty or not
     */
    boolean isEmpty()
    {
        if(this.top == 0){
            return true;
        }
            return false;
    }

    /** Getter for capacity variable.
     * @return: int of capacity of stack
     */
    int getCapacity() {
        return this.capacity;
    }

    /** Adds inputted item to top of the stack, throws errors if stack is full
     * or item is null, and updates top of stack. Also checks if capacity of
     * stack needs to be increased based on loadFactor to double it's size.
     * @param: Item of type E to push to top of Stack
     */
    void push(E x) throws StackOverflowError, NullPointerException
    {
        if(x == null){
            throw new NullPointerException();
        }
        if(isFull()){
            throw new StackOverflowError();
        }

        /** If the current spot/total capacity is greater then load factor,
        expand the list by two and copy everything over*/
        if( ((double)top  / (double)capacity) > (double)loadFactor){
            capacity *= 2;
            E arr2[] = (E[]) new Object[capacity];
            for(int i = 0; i < top; i++){
                arr2[i] = arr[i];
            }
            // Switch reference of arr to the larger new array.
            arr = arr2;
        }
        /**No matter what, the inputted x needs to be inserted at the top
         * or next free index of the array. For example, if the list is empty,
         * arr[0] = x, and then top is updated to 1 so the next time arr[1] is
         * updated.
         */
        arr[top] = x;
        top++;
    }

    /** Removes item from top of Stack, throws error if Stack is empty, also
     * shrinks capacity by half if neccessary based on shrinkFactor. Also
     * subtracts one from top.
     * @return: Item of type E that was removed from Stack
     */
    E pop() throws EmptyStackException {
        if(top == 0){
            throw new EmptyStackException();
        }
        /**Halve capacity if shrinkFactor is greater then or equal to
         top/capacity */
        if((double)top / (double)capacity <= (double)shrinkFactor){
            capacity = capacity / 2;
        }

        //Create a new array no matter what since an item has to be removed
        E arr2[] = (E[]) new Object[capacity];
        top--;
        /** Top is now 1 less, so the new list will contain all items up to that
        new top point */
        for(int i = 0; i < top; i++){
            arr2[i] = arr[i];
        }
        /** Return the item that was stored at the top of arr which will now be
        empty since it has been popped.*/
        E item = arr[top];
        arr = arr2;
        return item;
    }

    /** Returns top item of stack without removing it, throws
     * EmptyStackException if Stack is empty.
     * @return: Item of type E that is on top of Stack.
     */
    E peek() throws EmptyStackException{
        if(top == 0){
            throw new EmptyStackException();
        }
        /** Top represents the index above all items in the stack, so the
         * highest possible index will always be top -1. For example, if the
         * array is length 1, top will be 1. To access the highest point,
         * you need to get arr[0].*/
        return(arr[top - 1]);
    }

    /** Removes everything from Stack.
     */
    void clear(){
        E arr2[] = (E[]) new Object[capacity];
        arr = arr2;
    }

    /** Checks if Stack is full.
     * @return: Boolean on whether Stack is full.
     */
    boolean isFull(){
        if(top == capacity){
            return true;
        }
        return false;
    }

    /** Returns current size of Stack.
     * @return: int of current size of Stack.
     */
    int currentSize(){
        return top;
    }


    /** Removes item from top of Stack, throws error if Stack is empty, also
     * shrinks capacity by half if neccessary based on shrinkFactor. Also
     * subtracts one from top.
     * @return: Item of type E that was removed from Stack
     */
    E[] multiPop(int k) throws EmptyStackException{
        if(top == 0){
            throw new EmptyStackException();
        }

        if(k > currentSize()){
            E arr2[] = (E[]) new Object[capacity];
            arr2 = arr;
            clear();
            return arr2;
        }
        E arr2[] = (E[]) new Object[k];
        for(int i = 0; i < k; i++){
            arr2[i] = pop();
        }
        return arr2;
    }

    /**Pushes multiples items to the top of the Stack. Throws
     * NullPointerException if array is empty and StackOverflowError is there
     * is no room in the Stack.
     * @return: Array of E values to push to top of Stack.
     */
    void multiPush(E[] arr) throws StackOverflowError, NullPointerException{
        if(arr == null){
            throw new NullPointerException();
        }
        if(arr.length + top > capacity){
            throw new StackOverflowError();
        }
        for(int i = 0; i < arr.length; i++){
            push(arr[i]);
        }
    }

    /** Reverses order of Stack, throws EmptyStackException is there is nothing
     * in Stack.
     */
    void reverse() throws EmptyStackException{
        if(top == 0){
            throw new EmptyStackException();
        }
        E arr2[] = (E[]) new Object[capacity];
        int newCount = 0;
        for(int i = currentSize() - 1; i >= 0; i--){
            arr2[newCount] = arr[i];
            newCount ++;
        }
        arr = arr2;
    }

    /** Pushes item to top of stack if the previous top of Stack is different to
     * it. Returns true or false based on if the push is successful.
     * return: Boolean based on whether E item is unique to top of stack.
     */
    boolean pushUnique(E x) throws StackOverflowError, NullPointerException{
        if(isFull()){
            throw new StackOverflowError();
        }
        if(x == null){
            throw new NullPointerException();
        }
        if(arr[top-1].equals(x)){
            return false;
        }
            push(x);
            return true;
    }

    /** Returns index of item that matches inputted index, or -1 if the searched
     * for item is not in the Stack.
     * @return: Index of searched for item in Stack or -1
     */
    int search(E x) throws EmptyStackException{
        if(isEmpty()){
            throw new EmptyStackException();
        }
        for(int i = 0; i < top; i++){
            if(arr[i].equals(x)){
                return top - i;
            }
        }
        return -1;
    }

}