//To be completed

package toBeCompleted.stage1;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Dice {

    private static final AtomicInteger nextId = new AtomicInteger();
    private int id;
    private static final Random random = new Random();
    private static final int DEF_FACES = 6;
    private int faces;
    private int value;

    /**
     * Random Number Generator
     * 
     * @param min is the lower bound of the range
     * @param max is the upper bound of the range
     * @return a random value between @param min and @param max
     */
    private int rng(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * 
     * @param seed is the value provided to the RNG
     */
    public static void testReset(long seed, int next) {
    	random.setSeed(seed);
    	nextId.set(next);
    }

    /**
     * set the dice's identifier
     * setup a new random number generator
     * set faces to the value provided using the setter,
     * @param faces the number of sides on the dice.
     */
    public Dice(int faces) {
        id = nextId.incrementAndGet();
        //random = new Random();
        this.faces = faces;
    }
    
    /**
     * set the dice's identifier
     * setup a new random number generator
     * set faces to the default value.
     */
    public Dice() {
        this(DEF_FACES);
    }
    
    /**
     * @return the dice's identifier
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return the dice's current face value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * @return the dice's number of faces
     */
    public int getFaces() {
        return faces;
    }
    
    /**
     * set the face value to a new
     * random value between 1 and faces, inclusive
     */
    public void roll() {
        value = rng(1, faces);
    }

    /**
     * format example: if id == 3, value == 4 and faces == 7 then
     * method should return "3:4/7"
     * @return String representation of the object.<br>
     */
    public String toString() {
        return getId() + ":" + getValue() + "/" + getFaces();
    }
    
    /**
    *
    * @param other Compares this object with the specified object for order.
    * @return -1 if calling object is worth less than parameter object.<br>
    *  1 if calling object is worth more than parameter object.<br>
    *  0 if calling object is worth the same as parameter object.<br>
    */
    public int compareTo(Dice other) {
        if(getValue() < other.getValue()) {
                return -1;
        }
        if(getValue() > other.getValue()) {
                return 1;
        }
        return 0;
    }

}
