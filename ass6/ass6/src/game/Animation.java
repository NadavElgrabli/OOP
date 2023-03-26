package game;
import biuoop.DrawSurface;

/**
 * @author Nadav Elgrabli
 * animation interface
 */
public interface Animation {

    /**
     * run ones frame.
     * @param d drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     *  stop running frame.
     * @return true / false.
     */
    boolean shouldStop();
}