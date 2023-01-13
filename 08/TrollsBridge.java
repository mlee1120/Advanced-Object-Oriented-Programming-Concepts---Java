/*
 * This is file illustrates TrollsBridge.java from hw9.
 */
package hw9;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Monitor class for the woolies
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */
public class TrollsBridge {

    /** the first section of the troll's message for making woolies lineup */
    private final static String SCOWL_MESSAGE_1 = "The troll scowls \"Get in line!\" when ";
    /** the last section of the troll's message for making woolies lineup */
    private final static String SCOWL_MESSAGE_2 = " shows up at the bridge.";
    /** the message for announcing that a woolie starts crossing the bridge */
    private final static String CROSS_MESSAGE = " is starting to cross.";
    /** maximum number of passage (limitation) */
    private final int maxOnBridge;
    /** current number of woolies crossing this bridge */
    private int onBridge = 0;
    /** a thread-safe linked queue storing the woolies waiting to crossthe bridge in the order of arrival */
    private final ConcurrentLinkedQueue<Woolie> myQueue = new ConcurrentLinkedQueue<>();

    /**
     * The constructor sets up a new TrollsBridge object. The object represents a bridge,
     * which has limitation of passage, with a mean-spirited troll.
     *
     * @param maxOnBridge maximum number of passage (limitation)
     */
    public TrollsBridge(int maxOnBridge) {
        this.maxOnBridge = maxOnBridge;
    }

    /**
     * This method simulates a woolie asking the troll for permission to cross the bridge. The woolie is first told
     * to wait in line, and the troll checks if the bridge is full or not. If the bridge is full, the woolie would
     * have to wait in line. If the bridge is not full, the first woolie in the line can start crossing the bridge.
     *
     * @param aWoolie the woolie who is asking for permission to cross the bridge
     * @throws InterruptedException a checked exception which will be thrown when a thread is interrupted
     *                              during waiting (.wait()) or sleeping (.sleep()).
     */
    public synchronized void enterBridgePlease(Woolie aWoolie) throws InterruptedException {
        System.out.println(SCOWL_MESSAGE_1 + aWoolie.getWoolie() + SCOWL_MESSAGE_2);
        myQueue.add(aWoolie);
        // monitor: if the bridge is full or the woolie is not the first woolie in line, the woolie has to wait
        while (this.isFull() || myQueue.peek() != aWoolie) {
            this.wait();
        }
        System.out.println(aWoolie.getWoolie() + CROSS_MESSAGE);
        myQueue.remove();
        // this is the most critical line in this synchronized method (dealing with shared variable)
        onBridge += 1;
    }

    /**
     * This method simulates a woolie leaving the bridge.
     *
     * @param aWoolie the woolie who is leaving the bridge
     */
    public synchronized void leave(Woolie aWoolie){
        System.out.println(aWoolie.getWoolie() + " leaves at " + aWoolie.getSide() + ".");
        // changes the woolie's current location
        aWoolie.passed();
        // this is the most critical line in this synchronized method (dealing with shared variable)
        onBridge -= 1;
        // notifies all waiting woolies (threads) (.notify() randomly notifies one thread => might wait forever)
        this.notifyAll();
    }

    /**
     * This method returns if it is full or not.
     *
     * @return if the bridge has reached its maximum number of passage
     */
    public boolean isFull() {
        return maxOnBridge - onBridge == 0;
    }

}
