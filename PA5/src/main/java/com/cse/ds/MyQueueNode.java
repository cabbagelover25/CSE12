package com.cse.ds;

public class MyQueueNode<E> {

    private MyQueueNode<E> next;
    private E element;

    public MyQueueNode(E elem) {
        this.element = elem;
        this.next = null;
    }

    public MyQueueNode<E> getNext() {
      return this.next;
    }

    public void setNext(MyQueueNode<E> node) {
      this.next = node;
    }

    public E getElement() {
      return this.element;
    }

}
