package hit;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.Ball;
import gui.collision.Block;
import game.GameLevel;

/**
 * @author Nadav Elgrabli
 * Class that removes ball from game.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game game
     * @param removedBalls counter of balls removed.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /*
    Blocks that are hit should be removed
    from the game. Remember to remove this listener from the block
    that is being removed from the game.
    */

    /**
     * Blocks that are hit should be removed from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit block that is being hit.
     * @param hitter ball that hits the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
