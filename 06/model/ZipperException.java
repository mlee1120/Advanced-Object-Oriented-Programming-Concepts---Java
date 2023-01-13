/*
 * This is file illustrates ZipperException.java from hw7.
 */
package hw7.model;

/**
 * a class of a custom checked exception
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class ZipperException extends Exception {
    /** the message of this exception */
    String message = "Error processing most recent command!\nInvalid arguments.";

    /**
     * the constructor of ZipperException
     *
     * @param msg     virtual machine crashing message
     * @param message the message of this exception
     */
    public ZipperException(String msg, String message) {
        super(msg);
        this.message = message;
    }

    /**
     * constructor overload
     *
     * @param msg virtual machine crashing message
     */
    public ZipperException(String msg) {
        super(msg);
    }

    /**
     * @return the message of this exception
     */
    @Override
    public String toString() {
        return this.message;
    }
}
