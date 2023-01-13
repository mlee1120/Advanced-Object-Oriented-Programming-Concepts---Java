/*
 * This file illustrates TestPassenger.java from hw5.
 */
package hw5.test;

import hw5.Passenger;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test for the passenger class
 *
 *  @author Krystian Alexander Derhak, kad4374@rit.edu
 *  @author Michael Lee, ml3406@rit.edu
 */
public class TestPassenger {
    private static Passenger passenger1, passenger2, passenger3, passenger4, passenger5, passenger6, passenger7;

    public TestPassenger() {
        passenger1 = new Passenger("aaa", "A", 1);
        passenger2 = new Passenger("bbb", "B", 10);
        passenger3 = new Passenger("ccc", "C", 25);
        passenger4 = new Passenger("ddd", "A", 25);
        passenger5 = new Passenger("aaa", "A", 1);
        passenger6 = new Passenger("bbb", "B", 15);
        passenger7 = new Passenger("kkk", "A", 1);
    }

    @Test
    public void test1toString() {
        assertEquals(passenger1.toString(), "Removing : aaa in seat A1");
    }

    @Test
    public void test2getName() {
        assertEquals(passenger1.getName(), "aaa");
    }

    @Test
    public void test3getGroup() {
        assertEquals(passenger2.getGroup(), "B");
    }

    @Test
    public void test4getSequence() {
        assertEquals(passenger3.getSequence(), 25);
    }

    @Test
    public void test5compareTo() {
        assertEquals(passenger1.compareTo(passenger2), 1);
        assertEquals(passenger4.compareTo(passenger1), -1);
        assertEquals(passenger1.compareTo(passenger5), 0);
        assertEquals(passenger1.compareTo(passenger7), 0);
        assertEquals(passenger2.compareTo(passenger6), 1);
    }

    @Test
    public void test6equals() {
        assertEquals(passenger1.equals(passenger2), false);
        assertEquals(passenger1.equals(passenger4), false);
        assertEquals(passenger1.equals(passenger5), true);
        assertEquals(passenger1.equals(passenger7), true);
    }

    @Test
    public void test7hashCode() {
        assertEquals(passenger1.hashCode(), 66);
        assertEquals(passenger2.hashCode(),
                passenger2.getGroup().hashCode() + passenger2.getSequence());
    }

}
