/*
 * This file illustrates TestIntExp.java from hw3.
 */
package hw3.test;

import hw3.Expression;
import hw3.IntExp;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * A test unit for the IntExp class.
 *
 * @author RIT CS
 */
public class TestIntExp {
    @Test
    public void testIntExpression() {
        Expression root = new IntExp(10);
        assertEquals(10, root.evaluate());
        assertEquals("10", root.emit());
    }
}
