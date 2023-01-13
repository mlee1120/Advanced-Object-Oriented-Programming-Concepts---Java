/*
 * This is file illustrates TestTollRecord.java from hw6.
 */
package hw6.test;

import hw6.TollRecord;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Tests TollRecord.java
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestTollRecord {
    private static TollRecord record1;
    private static TollRecord record2;
    private static TollRecord record3;
    private static TollRecord record4;

    /**
     * Creates 4 TollRecord for testing
     */
    @BeforeClass
    public static void init() {
        record1 = new TollRecord(1, 20, "TEST_1");
        record2 = new TollRecord(15, 32, "TEST_2");
        record3 = new TollRecord(35, 4, "TEST_3");
        record4 = new TollRecord(1, 20, "TEST_1");
    }

    /**
     * Tests getters
     */
    @Test
    public void test1Init() {
        assertEquals(1, record1.getaTime());
        assertEquals(20, record1.getaExit());
        assertEquals("TEST_1", record1.getTag());

        assertEquals(15, record2.getaTime());
        assertEquals(32, record2.getaExit());
        assertEquals("TEST_2", record2.getTag());

        assertEquals(35, record3.getaTime());
        assertEquals(4, record3.getaExit());
        assertEquals("TEST_3", record3.getTag());
    }

    /**
     * Tests method CompleteRecord(int eTime, int eExit) and getComplete()
     */
    @Test
    public void test2Complete() {
        record1.CompleteRecord(12, 22);
        record2.CompleteRecord(18, 53);
        record4.CompleteRecord(12, 22);

        assertEquals(12, record1.geteTime());
        assertEquals(22, record1.geteExit());
        assertTrue(record1.getComplete());

        assertEquals(18, record2.geteTime());
        assertEquals(53, record2.geteExit());
        assertTrue(record2.getComplete());

        assertFalse(record3.getComplete());

        assertEquals(12, record4.geteTime());
        assertEquals(22, record4.geteExit());
        assertTrue(record4.getComplete());
    }

    /**
     * Tests method charge()
     */
    @Test
    public void test3Charge() {
        assertEquals(1.3472000000000004, record1.charge());
        assertEquals(7.312, record2.charge());
        assertEquals(1.3472000000000004, record4.charge());
    }

    /**
     * Tests overridden methods equals(Object other), compareTo(TollRecord o), and hashCode()
     */
    @Test
    public void test5Equals() {

        assertEquals(record1, record4);
        assertFalse(record2.equals(record4));

        assertEquals(-1, record2.compareTo(record3));
        assertEquals(1, record2.compareTo(record1));
        assertEquals(0, record1.compareTo(record4));

        assertEquals(-1823839271, record1.hashCode());
        assertEquals(-1823839244, record2.hashCode());
        assertEquals(-1823839251, record3.hashCode());
        assertEquals(-1823839271, record4.hashCode());
    }

    /**
     * Tests method speeding()
     */
    @Test
    public void test6Speeding() {
        assertEquals("Vehicle TEST_1, starting at time 1\n" +
                "       from Saugerties - Woodstock - NY Route 32\n" +
                "       to Selkirk - NY Routes 144 & 396\n" +
                "       183.7 MpH\n", record1.speeding());
        assertEquals("Vehicle TEST_2, starting at time 15\n" +
                "       from Westmoreland - Rome - NY Route 233\n" +
                "       to Buffalo (Downtown) - Canada - Niagara Falls - I-190\n" +
                "       3656.0 MpH\n", record2.speeding());
        assertEquals("Vehicle TEST_1, starting at time 1\n" +
                "       from Saugerties - Woodstock - NY Route 32\n" +
                "       to Selkirk - NY Routes 144 & 396\n" +
                "       183.7 MpH\n", record1.speeding());
    }

}
