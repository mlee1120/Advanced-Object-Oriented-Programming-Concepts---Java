/*
 * This file illustrates IntExp.java from hw3.
 */
package hw3;

/**
 * Class that represents operand.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class IntExp implements Expression {
    /** value of the integer */
    int value;
    Expression left, right;

    /**
     * IntExp constructor that initializes all its instance fields
     *
     * @param value value of the integer
     */
    public IntExp(int input) {
        value = input;
        left = right = null;
    }

    /**
     * returns the integer value
     *
     * @return value (value of the integer)
     */
    public int evaluate() {
        return (value);
    }

    /**
     * Creates a string of the value of the integer
     *
     * @return a string of the value
     */
    public String emit() {
        return (String.valueOf(value));
    }
}
