package hit;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.Ball;
import gui.collision.Block;

/**
 * @author Nadav Elgrabli
 * classs that tracts the counter of the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter counter of score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Class that updates the score when block is hit.
     * @param beingHit block that is being hit
     * @param hitter ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}