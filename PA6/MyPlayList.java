/** Author: Samuel Kapusta
 * Email: skapusta@ucsd.edu
 * Date: April 17, 2019
 *
 * Contains MyPlayList Class that extends the AbstractList class.
 **/

package com.cse.ds;

//import com.sun.java.util.jar.pack.ConstantPool;

import java.util.*;

/** MyPlaylist class enables various functions to provide information on LinkedList structure
* made up of individual song nodes from the Song class. Contains a private nsongs variable
* that describes the total amount fo songs and a dummy Song variable that follows LinkedList implementation
* format.**/
public class MyPlayList<E> extends AbstractList<E> {

    private int nsongs;
    Song dummy;
    Random rand;

    //  Implementation of the MyPlayList Class

    /** Only 0-argument constructor is define */
    public MyPlayList()
    {
        this.rand = new Random(1234);
        this.nsongs = 0;
        this.dummy = new Song<E>(null);
    }

    /** Returns total amount of songs in MyPlayList.
     * @return Int of total songs
     **/
    @Override
    public int size() {
        return this.nsongs; // XXX-CHANGE-XXX
    }

    /** Returns the element of the Song at the inputted index of the LinkedList.
     * @param: Index of element to get
     * @return: Element of Song at index
     * **/
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        // Index out of bounds if index doesn't exist
        if(index >= this.nsongs || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Song movingSong = dummy;
        for(int i =0; i < index + 1; i++) {
            movingSong = movingSong.getNext();
        }
       return (E)movingSong.getElement(); //Java conversion causes forced typecast back to E
    }

    /** Add inputs a new song into the array at whatever index the user inputs.
     * The element parameter is stored as the new song's element.
     * @param: Index to add song at
     * @param: Element to be stored in new song
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException, NullPointerException {
        Song newSong = new Song<E>(element);
        if(index > this.nsongs || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(element == null) {
            throw new NullPointerException();
        }
        Song prevSong = dummy;
        for(int i = 0; i < index; i++){
            prevSong = prevSong.getNext()  ;
        }
        Song nextSong = prevSong.getNext();
        prevSong.setNext(newSong);

        if(nextSong != null) {
            nextSong.setPrev(newSong);
        }
        newSong.setNext(nextSong);
        newSong.setPrev(prevSong);
        nsongs += 1;
    }

    /** Adds new song of inputted element at the end of the LinkedList.
     * @param: Element to add into String.
     */
    @Override
    public boolean add(E element) throws NullPointerException
    {
        if(element == null){
            throw new NullPointerException();
        }

        Song newSong = new Song<E>(element);
        Song prevSong = dummy;
        while (prevSong.getNext() != null){
            prevSong = prevSong.getNext();
        }

        prevSong.setNext(newSong);
        newSong.setPrev(prevSong);
        nsongs += 1;
        return true;
    }

    /** Set changes the element of the Song at the specific
     * index of the LinkedList in the parameter.
     * @param: Index of the Song to change
     * @param: New element
     * @return: Old element is returned
     */
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException, NullPointerException
    {
        if(index > this.nsongs || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if(element == null){
            throw new NullPointerException();
        }
        Song loopSong = dummy;
        for(int i = 0; i < index + 1; i++){
            loopSong = loopSong.getNext();
        }

        E prevE = (E)loopSong.getElement();
        loopSong.setElement(element);

        return prevE; // XXX-CHANGE-XXX
    }

    /** Remove Song from linked list
     * @param: Index of song to remove at
     * @return: Element of Song that was removed.
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException
    {
        if(index > nsongs){
            throw new IndexOutOfBoundsException();
        }
        Song removeSong = dummy;
        for (int i = 0; i < index; i++){
            removeSong = removeSong.getNext();
        }
        E thingy = (E)removeSong.getElement();
        removeSong.remove();
        nsongs -= 1;
        return thingy; // XXX-CHANGE-XXX
    }

    /** Reset the Playlist to empty by using setNext on the dummy variable so that
     * the other Songs cannot be accessed and are thus delete in the eyes of the playlist.
     */
    @Override
    public void clear()
    {
        this.dummy.setNext(null);
        this.dummy.setPrev(null);
        this.dummy.setElement(null);
        nsongs = 0;
    }

    /** Checks if nsongs is equal to 0 to see if the playlist is empty
     * @return Boolean based on whether the playlist has any songs in it.
     */
    @Override
    public boolean isEmpty()
    {
        if(nsongs == 0){
        return true;
        }
        else{
            return false;// XXX-CHANGE-XXX
        }
    }

    //Shuffle playlist.
    public void shuffle()
    {
	if(nsongs <= 1){	
		return;
	}
        int firstInt = rand.nextInt(nsongs);
        int secondInt = rand.nextInt(nsongs);
        Song loopSong = dummy;
	while(loopSong.getNext() != null){
            loopSong = loopSong.getNext();
        }
	loopSong.setNext(loopSong.getPrev());
        loopSong.setPrev(dummy);
	while(loopSong.getPrev().getElement() != null){
		Song last = loopSong;
		loopSong = loopSong.getPrev();
		loopSong.setNext(loopSong.getPrev());
		loopSong.setPrev(last);
	}

        loopSong = dummy;
        for(int i = 0; i < secondInt + 1; i++){
            loopSong = loopSong.getNext();
        }
        Song secondSong = loopSong;

        Song firstPrev = firstSong.getPrev();
        Song secondPrev = secondSong.getPrev();
        firstSong.setPrev(secondPrev);
        secondSong.setPrev(firstPrev);

        Song firstNext = firstSong.getNext();
        Song secondNext = secondSong.getNext();
        firstSong.setNext(secondNext);
        secondSong.setNext(firstNext);
    }

    //Reverse playlist.
    public void reverse()
    {
        Song lastSong = dummy;
        for(int i = 0; i < nsongs;i++){
            lastSong = lastSong.getNext();
        }

        lastSong.setPrev(dummy);
        Song prevSong = lastSong.getPrev();
        lastSong.setNext(prevSong);
        prevSong.setPrev(lastSong);

        while (prevSong.getPrev() != dummy) {
            Song currentSong = prevSong;
            prevSong = currentSong.getPrev();
            prevSong.setPrev(currentSong);
            currentSong.setNext(prevSong);
        }
    }

    /** Creates a new object of type myListIterator.
 	*/
    public MyListIterator myListIterator() {
        return new MyPlayListIterator<E>(this.nsongs, this.dummy);
    }
}


