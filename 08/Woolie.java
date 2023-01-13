/*
 * This is file illustrates Woolie.java from hw9.
 */
package hw9;

/**
 * Woolie class
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class Woolie extends Thread {

    /** the woolie's name */
    private final String name;
    /** the crossing time of the woolie */
    private final Integer crossTime;
    /** the current location of the woolie */
    private String side;
    /** the bridge to cross */
    private final TrollsBridge monitor;

    /**
     * The constructor sets up a new Woolie object. It first names the thread by passing the name to its super class.
     * Secondly, it validates that all woolies should be at either Merctran or Sicstine. Otherwise, a WoolieException
     * would be thrown, which would be caught and terminates the program. At last, it initializes all attributes of
     * the new Woolie object.
     *
     * @param name the name of this woolie (and Thread)
     * @param crossTime the time this woolie takes to cross the bridge (in seconds)
     * @param side the current location of this woolie
     * @param monitor the bridge this woolie is going to cross
     */
    public Woolie(String name, Integer crossTime, String side, TrollsBridge monitor) {
        // naming the thread, which is helpful for debugging
        super(name);
        try {
            if (!side.equals(RunWoolies.SIDE_ONE) && !side.equals(RunWoolies.SIDE_TWO))
                throw new WoolieException("There are only two possible destinations: Merctran or Sicstine");
        } catch (WoolieException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }
        this.name = name;
        this.crossTime = crossTime;
        this.side = side;
        this.monitor = monitor;
    }

    /**
     * Getter for woolie's name
     *
     * @return the name of this Woolie object
     */
    public String getWoolie() {
        return name;
    }

    /**
     * Getter for current side
     *
     * @return the current location of this Woolie object
     */
    public String getSide() {
        return side;
    }

    /**
     * This method simulates a woolie trying to get onto the bridge.
     *
     * @throws InterruptedException a checked exception which will be thrown when a thread is interrupted
     *                              during waiting (.wait()) or sleeping (.sleep()).
     */
    public void getOnBridge() throws InterruptedException {
        // asks the troll for permission
        monitor.enterBridgePlease(this);

    }

    /**
     * This method simulates a woolie trying to leave the bridge.
     */
    public void getOffBridge() {
        monitor.leave(this);
    }

    /**
     * This method changes the current location of the woolie once it finishes crossing the bridge.
     */
    public void passed() {
        side = side.equals(RunWoolies.SIDE_ONE) ? RunWoolies.SIDE_TWO : RunWoolies.SIDE_ONE;
    }

    /**
     * This method returns the equality between this Woolie and another object by comparing their classes, names,
     * crossTimes, and sides.
     * Their monitors were not compared because there is only one bridge in this assignment. If there are more than
     * one bridges, we could just add one more condition for comparing their monitors and also override equals method
     * in TrollBridge, which is one of the advantages of OOP.
     *
     * @param obj the reference object with which to compare
     * @return boolean if two objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Woolie) {
            Woolie w = (Woolie) obj;
            result = this.name.equals(w.name) && this.crossTime.equals(w.crossTime) && this.side.equals(w.side);
        }
        return result;
    }

    /**
     * This method computes and returns the hash code of this object.
     *
     * @return the hash code of this Woolie object
     */
    @Override
    public int hashCode() {
        return name.hashCode() + crossTime.hashCode() + side.hashCode();
    }

    /**
     * If this method is called directly, the codes in this method would be executed
     * without creating a new thread (no concurrency).
     * If this method is called by calling .start(), a new thread would be created and the codes
     * in this method would be executed concurrently with the main thread.
     */
    @Override
    public void run() {
        try {
            this.getOnBridge();
            for (int i = 0; i < crossTime; i++) {
                sleep(1000);
                System.out.println("\t" + name + " " + (i + 1) + " seconds.");
            }
            this.getOffBridge();
        } catch (InterruptedException e) {
            System.err.println("Abort. Unexpected thread interruption.");
        }
    }
}
