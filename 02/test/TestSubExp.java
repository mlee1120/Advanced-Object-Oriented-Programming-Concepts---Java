/*
 * This file illustrates TestSubExp.java from hw3.
 */
package hw3.test;

import hw3.SubExp;
import hw3.Expression;
import hw3.IntExp;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the SubExp class.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestSubExp {
    @Test
    public void testSubExpressionInt() {
        Expression root = new SubExp(new IntExp(30), new IntExp(20));
        assertEquals(10, root.evaluate());
        assertEquals("(30 - 20)", root.emit());
    }

    @Test
    public void testSubExpressionComplex() {
        Expression root = new SubExp(
                new SubExp(new IntExp(100), new IntExp(30)),
                new SubExp(new IntExp(40), new IntExp(20)));
        assertEquals(50, root.evaluate());
        assertEquals("((100 - 30) - (40 - 20))", root.emit());
    }
}
