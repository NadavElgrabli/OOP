package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * @author Nadav Elgrabli
 * Class in charge of running th animation
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private KeyboardSensor keyboard;

    /**
     * Constructor.
     * @param gui gui
     * @param framesPerSecond frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * run animation mathod.
     * @param animation animation
     */
    public void run(Animation animation) {

        //create ne sleeper.
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * get key board sensor.
     * @return KeyboardSensor.
     */
    public KeyboardSensor getKeyBoard() {
        return this.keyboard;
    }
}