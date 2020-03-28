package com.cse.ds;

public class Song<E> {

    E data;
    Song next;
    Song prev;

    /** Constructor to create singleton Song */
    public Song(E element)
    {
        this.data = element;
    }
    /** Constructor to create singleton link it between previous and next
     *   @param element Element to add, can be null
     *   @param prevSong predecessor Song, can be null
     *   @param nextSong successor Song, can be null
     */
    public Song(E element, Song prevSong, Song nextSong)
    {
        this.data = element;
        this.prev = prevSong;
        this.next = nextSong;
    }
    /** Remove this Song from the list. Update previous and next Songs */
    public void remove()
    {
        Song previous = this.prev;
        previous.setNext(this.next);

        Song next = this.next;
        next.setPrev(this.prev);

        this.data = null;
    }
    /** Set the previous Song in the list
     *  @param p new previous Song
     */
    public void setPrev(Song p)
    {
        this.prev = p;
    }

    /** Set the next Song in the list
     *  @param n new next Song
     */
    public void setNext(Song n)
    {
        this.next = n;
    }

    /** Set the element
     *  @param e new element
     */
    public void setElement(E e)
    {
        this.data = e;
    }
    /** Accessor to get the next Song in the list */
    public Song getNext()
    {
        return this.next; // XXX-CHANGE-XXX
    }
    /** Accessor to get the prev Song in the list */
    public Song getPrev()
    {
        return this.prev; // XXX-CHANGE-XXX

    }
    /** Accessor to get the Songs Element */
    public E getElement()
    {
        return this.data;// XXX-CHANGE-XXX
    }
}


