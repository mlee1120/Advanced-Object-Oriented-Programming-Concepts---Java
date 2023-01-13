/*
 * This file illustrates TestMulExp.java from hw3.
 */
package hw3.test;

import hw3.MulExp;
import hw3.Expression;
import hw3.IntExp;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the MulExp class.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestMulExp {
    @Test
    public void testMulExpressionInt() {
        Expression root = new MulExp(new IntExp(10), new IntExp(20));
        assertEquals(200, root.evaluate());
        assertEquals("(10 * 20)", root.emit());
    }

    @Test
    public void testMulExpressionComplex() {
        Expression root = new MulExp(
                new MulExp(new IntExp(10), new IntExp(30)),
                new MulExp(new IntExp(40), new IntExp(20)));
        assertEquals(240000, root.evaluate());
        assertEquals("((10 * 30) * (40 * 20))", root.emit());
    }
}
