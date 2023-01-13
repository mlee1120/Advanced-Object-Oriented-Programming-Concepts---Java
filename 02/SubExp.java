/*
 * This file illustrates SubExp.java from hw3.
 */
package hw3;

/**
 * Class that executes subtraction.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class SubExp implements Expression {
    /** left and right operands */
    Expression left, right;

    /**
     * SubExp constructor that initializes all its instance fields
     *
     * @param left  left operand
     * @param right right operand
     */
    public SubExp(Expression L, Expression R) {
        left = L;
        right = R;
    }

    /**
     * Gives a number evaluation of Subtraction
     *
     * @param math store the evaluated value
     * @return math (the evaluated int value)
     */
    public int evaluate() {
        int math;
        math = left.evaluate() - right.evaluate();
        return (math);
    }

    /**
     * Creates a string of the expression with included parentheses
     *
     * @return a string expression
     */
    public String emit() {
        return ("(" + left.emit() + " - " + right.emit() + ")");
    }
}
