package hit;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.Ball;
import gui.collision.Block;
import game.Game;

/**
 * @author  Nadav Elgrabli
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game game
     * @param removedBlocks counter that counts how many blocks removed.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit block that is being hit.
     * @param hitter ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);

        //update counter by -1.
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}