/*
 * This is file illustrates WoolieException.java from hw9.
 */
package hw9;

/**
 * a class of a custom checked exception
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class WoolieException extends Exception {
    /**
     * The constructor sets up a new WoolieException by passing
     * the message up to its superclass.
     *
     * @param message the message about this exception
     */
    public WoolieException(String message) {
        super(message);
    }
}
