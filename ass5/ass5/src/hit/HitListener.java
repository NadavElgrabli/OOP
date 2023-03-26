package hit;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.Ball;
import gui.collision.Block;

/**
 * @author Nadav Elgrabli
 * Interface of listeners.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit. The hitter parameter is the Ball that's
     * doing the hitting.
     * @param beingHit block that is being hit
     * @param hitter ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}