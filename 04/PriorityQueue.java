/*
 * This file illustrates PriorityQueue.java from hw5.
 */
package hw5;

/**
 * PriorityQueue interface for classes to implement with their own underlying data structures.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public interface PriorityQueue<T>{

    T dequeue();

    void enqueue(T toInsert);

    boolean isEmpty();

}
