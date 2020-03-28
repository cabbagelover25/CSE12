package com.cse.ds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//17 test case
@FixMethodOrder(MethodSorters.JVM)
public class TestMailroom {

    static Mailroom mailroom;


    @Before
    public void populate(){
        mailroom = new Mailroom();
    }


    @Test
    public void testRegister() {
        Deliverable mail = new MyMail(1,
                                    "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                                    "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                                    "Hello World!");
        mailroom.registerItem(mail);
        Assert.assertTrue(mail == mailroom.deliverEarliest());

        //mailroom.deliverEarliest();
    }

    @Test
    public void testRegister2() {
        Deliverable mail = new MyMail(1,
                "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                "Hello World!");
        mailroom.registerItem(mail);
        System.out.println("Zipcode: " + mail.getZipCode());
        Assert.assertTrue(mail == mailroom.deliverEarliest("92632"));

        //mailroom.deliverEarliest();
    }

    @Test
    public void testRegister3() {
        Deliverable mail = new MyMail(1,
                "3811 Nobel Drive, La Jolla, CA, USA, 92037",
                "2231 Lebon Drive, La Jolla, CA, USA, 92632",
                "Hello World!");
        mailroom.registerItem(mail);
        System.out.println("Zipcode: " + mail.getZipCode());
        Assert.assertTrue(mail == mailroom.deliverEarliest("92632"));

        //mailroom.deliverEarliest();
    }

    @Test(expected = NullPointerException.class)
    public void testRegisterNull() {
        mailroom.registerItem(null);
    }



}
