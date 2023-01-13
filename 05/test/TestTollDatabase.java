/*
 * This is file illustrates TestTollDatabase.java from hw6.
 */
package hw6.test;

import hw6.TollDatabase;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests TollDatabase.java
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TestTollDatabase {
    private static TollDatabase data1;
    private static TollDatabase data2;

    /**
     * Creates two TollDatabase for testing
     */
    @BeforeClass
    public static void init() {
        data1 = new TollDatabase("small.txt");
        data2 = new TollDatabase("5guys.txt");
    }

    /**
     * Tests method summary()
     */
    @Test
    public void test1Summary() {
        assertEquals("\n2 completed trips\n", data1.summary());
        assertEquals("\n18 completed trips\n", data2.summary());
    }

    /**
     * Tests method printOnRoad()
     */
    @Test
    public void test2PrintOnRoad() {
        assertEquals("ON-ROAD REPORT\n" +
                "==============\n" +
                "[ BEN_KENOBI] on #45, time    62\n", data1.printOnRoad());
        assertEquals("ON-ROAD REPORT\n" +
                "==============\n" +
                "[     DR_WHO] on #15, time   458\n" +
                "[   JOE_NOTO] on #30, time   443\n", data2.printOnRoad());
    }

    /**
     * Tests method speederReport()
     */
    @Test
    public void test3SpeederReport() {
        assertEquals("\n" +
                "SPEEDER REPORT\n" +
                "==============\n" +
                "Vehicle I_AM_STROOT, starting at time 61\n" +
                "       from Rochester - Victor - I-490\n" +
                "       to Syracuse - Fulton - I-690 - NY Route 690\n" +
                "        94.6 MpH\n", data1.speederReport());
        assertEquals("\n" +
                "SPEEDER REPORT\n" +
                "==============\n" +
                "Vehicle DR_WHO, starting at time 10\n" +
                "       from Rochester - Victor - I-490\n" +
                "       to Kingston - NY Route 28 - Kingston-Rhinecliff Bridge\n" +
                "       973.6 MpH\n" +
                "Vehicle DR_WHO, starting at time 29\n" +
                "       from Mile Square Road\n" +
                "       to Buffalo - Cheektowaga - Walden Avenue\n" +
                "       1404.7 MpH\n" +
                "Vehicle DR_WHO, starting at time 76\n" +
                "       from Batavia - NY Route 98\n" +
                "       to Schenectady - Scotia - I-890 - NY Routes 5 & 5S\n" +
                "       2734.9 MpH\n" +
                "Vehicle DR_WHO, starting at time 94\n" +
                "       from Fultonville - Fonda - NY Route 30A\n" +
                "       to Dunkirk - Fredonia - NY Route 60\n" +
                "       Infinity MpH\n" +
                "Vehicle DR_WHO, starting at time 292\n" +
                "       from White Plains - Rye - Cross Westchester Expressway (I-287)\n" +
                "       to Buffalo (Downtown) - Canada - Niagara Falls - I-190\n" +
                "       858.3 MpH\n" +
                "Vehicle DR_WHO, starting at time 373\n" +
                "       from Schenectady - I-890 - NY Routes 7 & 146\n" +
                "       to Cross County Parkway - Mile Square Road\n" +
                "       2274.8 MpH\n" +
                "Vehicle JOE_NOTO, starting at time 140\n" +
                "       from Spring Valley - Nanuet - NY Route 59\n" +
                "       to New Paltz - Poughkeepsie - NY Route 299\n" +
                "       106.4 MpH\n" +
                "Vehicle JOE_NOTO, starting at time 203\n" +
                "       from Kingston - NY Route 28 - Kingston-Rhinecliff Bridge\n" +
                "       to Selkirk - NY Routes 144 & 396\n" +
                "       201.0 MpH\n" +
                "Vehicle THX_1138, starting at time 29\n" +
                "       from New Jersey - I-287 - NJ Route 17 South\n" +
                "       to Kingston - NY Route 28 - Kingston-Rhinecliff Bridge\n" +
                "       159.7 MpH\n", data2.speederReport());
    }

    /**
     * Tests method billingInfo()
     */
    @Test
    public void test4BillingInfo() {
        assertEquals("\n" +
                "BILLING INFORMATION\n" +
                "==============\n" +
                "[       DR_J] on #45, time    60; off #39, time   150: $ 2.46\n" +
                "[I_AM_STROOT] on #45, time    61; off #39, time   100: $ 2.46\n" +
                "Total: $ 4.92", data1.billingInfo());
        assertEquals("\n" +
                "BILLING INFORMATION\n" +
                "==============\n" +
                "[     DR_WHO] on #45, time    10; off #19, time    26: $10.38\n" +
                "[     DR_WHO] on # 3, time    29; off #52, time    47: $16.86\n" +
                "[     DR_WHO] on #48, time    76; off #26, time    81: $ 9.12\n" +
                "[     DR_WHO] on #28, time    94; off #59, time    94: $11.42\n" +
                "[     DR_WHO] on #18, time   153; off #22, time   241: $ 2.36\n" +
                "[     DR_WHO] on # 8, time   292; off #53, time   321: $16.59\n" +
                "[     DR_WHO] on #25, time   373; off # 4, time   377: $ 6.07\n" +
                "[   JOE_NOTO] on #12, time    39; off #19, time   119: $ 2.90\n" +
                "[   JOE_NOTO] on #14, time   140; off #18, time   170: $ 2.13\n" +
                "[   JOE_NOTO] on #19, time   203; off #22, time   216: $ 1.74\n" +
                "[ SNAKE_PLSK] on # 0, time   831; off #62, time  1911: $19.84\n" +
                "[   THX_1138] on #15, time    29; off #19, time    52: $ 2.45\n" +
                "[   THX_1138] on #44, time    56; off #37, time   119: $ 2.53\n" +
                "[   THX_1138] on #23, time   133; off #25, time   162: $ 0.48\n" +
                "[   THX_1138] on #57, time   196; off #49, time   217: $ 0.76\n" +
                "[   THX_1138] on #50, time   373; off #54, time   400: $ 0.30\n" +
                "[   TOP-5309] on #14, time  1745; off #15, time  1767: $ 0.29\n" +
                "[   TOP-5309] on #43, time  1826; off #45, time  1932: $ 0.43\n" +
                "Total: $106.66", data2.billingInfo());
    }
}
