/*
 * This file illustrates Expression.java from hw3.
 */
package hw3;

/**
 * Interface Expression
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public interface Expression {
    /**
     * Gives a number evaluation of
     *
     * @return the calculated number of the expression
     */
    int evaluate();

    /**
     * Creates a string of the expression with included parentheses
     *
     * @return a string expression
     */
    String emit();
}
