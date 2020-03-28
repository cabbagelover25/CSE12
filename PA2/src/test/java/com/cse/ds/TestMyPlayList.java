package com.cse.ds;
import org.junit.*;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.JVM)
public class TestMyPlayList {
    static MyPlayList obj = null;

    @BeforeClass
    public static void setUpBeforeClass() {
        obj = new MyPlayList();
    }
    @Before
    public void populatePlayList(){
        obj.clear();
        for(int i=0;i<10;i++){
            obj.add(i);
        }
    }
    @Test
    public void testNumberOfSongs() {
        Assert.assertEquals(obj.size(), 10);
    }

    @Test
    public void testAddSongs() {
        obj.add(10);
        Assert.assertEquals(obj.get(10), 10);
    }


    @Test
    public void testIterator() {
        MyListIterator<Song> iter = obj.myListIterator();
        for(int i=0;i<obj.size()/2;i++){
            iter.next();
        }
       // Assert.assertEquals(iter.next(), 5);
        //Assert.assertEquals(iter.previous(), 4);
        //Assert.assertEquals(iter.next(), 5);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        obj.set(100,1);
    }


}


