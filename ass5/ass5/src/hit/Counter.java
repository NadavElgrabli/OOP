package hit;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Counter class to count things we wish to count.
 */
public class Counter {
    private int count = 0;

    /**
     * add number to current count.
     * @param number number we want to increase count by.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number to current count.
     * @param number number we want to decrease count by.
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * get the current count.
     * @return count.
     */
    public int getValue() {
        return this.count;
    }
}