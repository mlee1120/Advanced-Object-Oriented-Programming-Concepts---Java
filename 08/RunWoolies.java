/*
 * This is file illustrates RunWoolies.java from hw9.
 */
package hw9;

import org.junit.Test;

/*
 * RunWoolies.java
 * Provided source file as a starter for a test suite.
 * See the todo text for what to complete.
 */

/**
 * Test the TrollsBridge and Woolies simulation.
 * Test by creating a bunch of Woolies and let them cross the TrollsBridge.
 *
 * @author CS @ RIT.EDU
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class RunWoolies {

    /**
     * SIDE_ONE is Merctran.
     */
    public final static String SIDE_ONE = "Merctran";

    /**
     * SIDE_TWO is Sicstine.
     */
    public final static String SIDE_TWO = "Sicstine";

    @Test
    /**
     * test0 is Test Scenario 0, an extremely simple, non-waiting test.
     * test0 provides an example template/pattern for writing a test case.
     */
    public void test0() {

        System.out.println("Begin test0. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        // Set an optional, test delay to stagger the start of each woolie.
        int delay = 4000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 4, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
                break;
            }
        }
        System.out.println("\n=============================== End test0.");
    }


    @Test
    /**
     * test1 is Test Scenario 1, another fairly simple simulation run.
     * test1 provides another example for writing a test case.
     */
    public void test1() {

        System.out.println("Begin test1. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        int delay = 1000;

        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Al", 3, SIDE_ONE, trollBridge),
                new Woolie("Bob", 2, SIDE_ONE, trollBridge),
                new Woolie("Cathy", 2, SIDE_TWO, trollBridge),
                new Woolie("Doris", 3, SIDE_TWO, trollBridge),
                new Woolie("Edith", 3, SIDE_ONE, trollBridge),
                new Woolie("Fred", 2, SIDE_TWO, trollBridge),
        };

        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }

        System.out.println("\n=============================== End test1.");
    }

    @Test
    /**
     * test2 is Test Scenario 2, a simulation of the Questions part from hw9.
     * In test2, ten woolies with their own crossing times come to the bridge on second after another.
     * When there are three woolies crossing the bridge, other woolies will have to wait in line.
     */
    public void test2() {

        System.out.println("Begin test2. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        System.out.println("Simulates the HW Question in section 2\n");

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        // Set an OPTIONAL, test delay to stagger the start of each woolie.
        int delay = 1000;
        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("Alf", 7, SIDE_ONE, trollBridge),
                new Woolie("Bev", 4, SIDE_ONE, trollBridge),
                new Woolie("Cal", 6, SIDE_TWO, trollBridge),
                new Woolie("Deb", 3, SIDE_TWO, trollBridge),
                new Woolie("Eli", 3, SIDE_ONE, trollBridge),
                new Woolie("Fay", 2, SIDE_TWO, trollBridge),
                new Woolie("Gia", 4, SIDE_ONE, trollBridge),
                new Woolie("Hal", 3, SIDE_TWO, trollBridge),
                new Woolie("Ira", 3, SIDE_ONE, trollBridge),
                new Woolie("Kim", 2, SIDE_ONE, trollBridge),
        };
        // Run them by calling their start() method.
        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test2.");
    }


    @Test
    /**
     * test3 is Test Scenario 3, a simulation of 26 woolies coming one after another with a delay
     * of 0.1 seconds and crossing the bridge from their current sides to the other.
     */
    public void test3() {
        System.out.println("Begin test3. ===============================\n");

        Thread init = Thread.currentThread();      // init spawns the Woolies

        System.out.println("Simulates the HW Question in section 2\n");

        // Create a TrollsBridge of capacity 3.
        TrollsBridge trollBridge = new TrollsBridge(3);

        // Set an OPTIONAL, test delay to stagger the start of each woolie.
        int delay = 100;
        // Create the Woolies and store them in an array.
        Thread peds[] = {
                new Woolie("A", 7, SIDE_ONE, trollBridge),
                new Woolie("B", 4, SIDE_ONE, trollBridge),
                new Woolie("C", 6, SIDE_TWO, trollBridge),
                new Woolie("D", 3, SIDE_TWO, trollBridge),
                new Woolie("E", 3, SIDE_ONE, trollBridge),
                new Woolie("F", 10, SIDE_TWO, trollBridge),
                new Woolie("G", 4, SIDE_ONE, trollBridge),
                new Woolie("H", 3, SIDE_TWO, trollBridge),
                new Woolie("I", 3, SIDE_ONE, trollBridge),
                new Woolie("j", 6, SIDE_ONE, trollBridge),
                new Woolie("K", 2, SIDE_ONE, trollBridge),
                new Woolie("L", 1, SIDE_TWO, trollBridge),
                new Woolie("M", 1, SIDE_ONE, trollBridge),
                new Woolie("N", 8, SIDE_TWO, trollBridge),
                new Woolie("O", 3, SIDE_TWO, trollBridge),
                new Woolie("P", 2, SIDE_TWO, trollBridge),
                new Woolie("Q", 14, SIDE_ONE, trollBridge),
                new Woolie("R", 2, SIDE_ONE, trollBridge),
                new Woolie("S", 1, SIDE_TWO, trollBridge),
                new Woolie("T", 1, SIDE_ONE, trollBridge),
                new Woolie("U", 19, SIDE_ONE, trollBridge),
                new Woolie("V", 21, SIDE_TWO, trollBridge),
                new Woolie("W", 4, SIDE_TWO, trollBridge),
                new Woolie("X", 5, SIDE_ONE, trollBridge),
                new Woolie("Y", 2, SIDE_TWO, trollBridge),
                new Woolie("Z", 1, SIDE_ONE, trollBridge),
        };
        // Run them by calling their start() method.
        for (int j = 0; j < peds.length; ++j) {
            // Run them by calling their start() method.
            try {
                peds[j].start();
                Thread.sleep(delay);         // delay start of next woolie (makes all woolies come in desired order)
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        // Now, the test must give the woolies time to finish their crossings.
        for (int j = 0; j < peds.length; ++j) {
            try {
                peds[j].join();              // wait for next woolie to finish
            } catch (InterruptedException e) {
                System.err.println("Abort. Unexpected thread interruption.");
            }
        }
        System.out.println("\n=============================== End test3.");
    }
}
