/*
 * This file illustrates TestLinkedQueue.java from hw5.
 */
package hw5.test;

import hw5.LinkedQueue;
import hw5.Passenger;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test for the LinkedQueue class
 *
 *  @author Krystian Alexander Derhak, kad4374@rit.edu
 *  @author Michael Lee, ml3406@rit.edu
 */
public class TestLinkedQueue {
    private static LinkedQueue<Passenger> lq;

    public TestLinkedQueue() {
        lq = new LinkedQueue<>();
        lq.enqueue(new Passenger("aaa", "B", 30));
        lq.enqueue(new Passenger("bbb", "C", 40));
        lq.enqueue(new Passenger("bbb", "C", 40));
        lq.enqueue(new Passenger("ccc", "A", 1));
        lq.enqueue(new Passenger("ddd", "B", 10));
        lq.enqueue(new Passenger("eee", "A", 15));
        lq.enqueue(new Passenger("fff", "C", 1));
    }

    @Test
    public void test1isEmpty() {
        assertEquals(false, lq.isEmpty());
    }

    @Test
    public void test2en_dequeue() {
        assertEquals(lq.dequeue(), new Passenger("ccc", "A", 1));
        assertEquals(lq.dequeue(), new Passenger("eee", "A", 15));
        assertEquals(lq.dequeue(), new Passenger("ddd", "B", 10));
        assertEquals(lq.dequeue(), new Passenger("aaa", "B", 30));
        assertEquals(lq.dequeue(), new Passenger("fff", "C", 1));
        assertEquals(lq.dequeue(), new Passenger("bbb", "C", 40));
    }
}
