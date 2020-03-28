package com.cse.ds;

import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class TestMyQueue {

    static MyQueue<Integer> intQueue = null;

    @Before
    public void testEnqueue() {
        intQueue = new MyQueue();
    }


    @Test
    public void testSize() {
        Assert.assertTrue(intQueue.size() == 0);
    }

    @Test
    public void testEnqueueIsEmpty() {
        intQueue.enqueue(1);
        Assert.assertFalse(intQueue.isEmpty());
    }



}
