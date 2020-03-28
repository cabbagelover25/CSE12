package com.cse.ds;


import java.util.NoSuchElementException;

/** ListIterator implementation */
public class MyPlayListIterator<E> implements MyListIterator<E> {

    private int nsongs;
    private int position;
    Song dummy;
    Song left;
    Song right;
    int forward = 0;

    public MyPlayListIterator(int i, Song dummy){
        this.nsongs = i;
        this.position = 0;
        this.dummy = dummy;
        this.left = dummy;
        this.right = left.getNext();

    }
    @Override
    public boolean hasNext() {
        if(dummy.getNext() == null && right != null){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public E next() throws NoSuchElementException {

        if (right == null){
            throw new NoSuchElementException();
        }

        position += 1;
        left = right;
        right = right.getNext();
        forward = 1;
        return (E) left.getElement();
    }

    @Override
    public boolean hasPrevious() {
        if (left == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public E previous() throws NoSuchElementException{
        if(left == null){
            throw new NoSuchElementException();
        }
        position -= 1;
        right = left;
        left = left.getNext();
        forward = -1;
        return (E)right.getElement();
    }

    @Override
    public int nextIndex() {
        return position;
                // maybe +1 not sure;
    }

    @Override
    public int previousIndex() {
        return position -= 1; // XXX-CHANGE-XXX
    }

    @Override
    public void set(E o) throws NullPointerException, IllegalStateException {
        if (o == null){
            throw new NullPointerException();
        }
        if (forward == 0){
            throw new IllegalStateException();
        }
        if(forward == 1){
            left.setElement(o);
        }
        if (forward == -1){
            right.setElement(o);
        }
    }



}
