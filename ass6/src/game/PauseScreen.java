package game;

import biuoop.DrawSurface;

/**
 * @author Nadav Elgrabli
 * Pause the screen class.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Draw one frame.
     * @param d drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * stop the animation.
     * @return boolean.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}