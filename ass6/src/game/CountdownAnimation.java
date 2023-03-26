package game;


import biuoop.DrawSurface;
import biuoop.GUI;
import gui.SpriteCollection;

/**
 * @author Nadav Elgrabli
 * CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private double numOfMilliSeconds;
    private SpriteCollection gameScreen;
    private GUI gui;
    private boolean running = true;
    private int milliSecondsPerFrame;
    private int counter = 0;


    /**
     * Constructor.
     * @param numOfSeconds number of seconds the count down will take.
     * @param countFrom count from which number.
     * @param gameScreen gamescreen.
     * @param milliSecondsPerFrame number of milli seconds per frame.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              int milliSecondsPerFrame) {
        this.countFrom = countFrom;
        this.numOfMilliSeconds = (1000 * numOfSeconds) / countFrom;
        this.gameScreen = gameScreen;
        this.milliSecondsPerFrame = milliSecondsPerFrame;
    }

    /**
     * draw on frame.
     * @param d drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        counter = counter + milliSecondsPerFrame;
        for (int i = 1; i <= countFrom + 1; i++) {

            //if condition that checks if counter passed the max milliseconds it count should take.
            if (counter >= (countFrom + 1) * numOfMilliSeconds) {
                this.running = false;
            }

            //if condition to check that counter is in range of right number of milli seconds.
            if (counter < i * numOfMilliSeconds && ((i - 1) * numOfMilliSeconds) <= counter) {
                d.drawText(10, d.getHeight() / 2, countDownString(i), 32);
            }
        }
    }

    /**
     * the string of the countdown.
     * @param i integer.
     * @return string.
     */
    public String countDownString(int i) {
        String result = "";

        //for loop to run on the given integer.
        for (int j = 1; j <= i; j++) {

            //if condition checks if countFrom is over, print GO
            if (j == countFrom + 1) {
                result = result + "(GO)";
            } else {

            //print the current num in countFrom + "..."
            result = result + Integer.toString(countFrom - j + 1) + "...";
            }
        }
        return result;
    }

    /**
     * method that says the run should stop.
     * @return true/false
     */
    public boolean shouldStop() {
        return !this.running;
    }
}