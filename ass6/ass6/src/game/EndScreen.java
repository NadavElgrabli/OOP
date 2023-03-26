package game;
import biuoop.DrawSurface;

/**
 * @author Nadav Elgrabli
 * class of end screen animation.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private String message;

    /**
     * Constructor.
     * @param message string of message of end screen.
     */
    public EndScreen(String message) {
        this.stop = false;
        this.message = message;
    }

    /**
     * Draw the frame of end screen.
     * @param d drawSurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, message, 32);
    }

    /**
     *
     * @return true/false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
