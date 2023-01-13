/*
 * This file illustrates TestDivExp.java from hw3.
 */
package hw3.test;

import hw3.DivExp;
import hw3.Expression;
import hw3.IntExp;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the DivExp class.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestDivExp {
    @Test
    public void testDivExpressionInt() {
        Expression root = new DivExp(new IntExp(50), new IntExp(10));
        assertEquals(5, root.evaluate());
        assertEquals("(50 / 10)", root.emit());
    }

    @Test
    public void testModExpressionComplex() {
        Expression root = new DivExp(
                new DivExp(new IntExp(100), new IntExp(5)),
                new DivExp(new IntExp(10), new IntExp(2)));
        assertEquals(4, root.evaluate());
        assertEquals("((100 / 5) / (10 / 2))", root.emit());
    }
}
