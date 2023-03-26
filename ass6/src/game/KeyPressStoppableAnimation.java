package game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Nadav Elgrabli
 * decorator class to add "wait for key" behavior.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private Animation animation;
    private String key;
    private boolean running;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * @param sensor KeyboardSensor.
     * @param key String
     * @param animation Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.sensor = sensor;
        this.key = key;
        running = true;
    }

    /**
     * run one frame.
     * @param d drawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            running = false;
        }
        animation.doOneFrame(d);
    }

    /**
     *
     * @return stop the run.
     */
    @Override
    public boolean shouldStop() {
        return !running;
    }
}