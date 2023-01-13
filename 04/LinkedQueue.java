/*
 * This file illustrates LinkedQueue.java from hw5.
 */
package hw5;

/**
 * Creates the data type of LinkedQueue with the linked list as underlying data structure.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

import java.util.LinkedList;

public class LinkedQueue<T extends Comparable<T>> implements PriorityQueue<T> {

    private LinkedList<T> theLinked;


    /**
     * The constructor for the LinkedQueue
     */
    public LinkedQueue() {
        theLinked = new LinkedList<T>();
    }

    /**
     * Removes and returns the item at the front of the queue
     *
     * @param args used for testing only
     */
    public static void main(String args[]) {
        LinkedQueue<String> lq = new LinkedQueue<String>();
        lq.enqueue("Hi");
        lq.enqueue("Hi There");
        lq.enqueue("Apple");
        lq.enqueue("House");
        lq.enqueue("Dog");
        while (!lq.isEmpty()) {
            System.out.println(lq.dequeue());
        }
    }

    /**
     * Removes and returns the item at the front of the queue
     *
     * @return T the item at the front of the queue otherwise
     * returns a null
     */
    @Override
    public T dequeue() {
        return theLinked.remove(0);
    }

    /**
     * Add an item to the queue at the appropriate location
     *
     * @param toInsert the item to insert in the queue
     */
    @Override
    public void enqueue(T toInsert) {
        if (theLinked.isEmpty()) theLinked.add(toInsert);
        else {
            if (toInsert.compareTo(theLinked.get(theLinked.size() - 1)) < 0) theLinked.add(toInsert);
            else if (toInsert.compareTo(theLinked.get(theLinked.size() - 1)) > 0) {
                for (int i = 0; i < theLinked.size(); i++) {
                    if (toInsert.compareTo(theLinked.get(i)) > 0) {
                        theLinked.add(i, toInsert);
                        break;
                    }
                }
            } else System.out.println("A passenger with the same group and sequence has already registered.");
        }
    }

    /**
     * Is there anything in the queue?
     *
     * @return true if the queue is empty; otherwise returns false
     */
    @Override
    public boolean isEmpty() {
        return theLinked.isEmpty();
    }
}
