/*
 * This file illustrates TestModExp.java from hw3.
 */
package hw3.test;

import hw3.ModExp;
import hw3.Expression;
import hw3.IntExp;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the ModExp class.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestModExp {
    @Test
    public void testModExpressionInt() {
        Expression root = new ModExp(new IntExp(50), new IntExp(15));
        assertEquals(5, root.evaluate());
        assertEquals("(50 % 15)", root.emit());
    }

    @Test
    public void testModExpressionComplex() {
        Expression root = new ModExp(
                new ModExp(new IntExp(30), new IntExp(20)),
                new ModExp(new IntExp(15), new IntExp(10)));
        assertEquals(0, root.evaluate());
        assertEquals("((30 % 20) % (15 % 10))", root.emit());
    }
}
