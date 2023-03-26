package game;
import gui.Sprite;
import gui.collision.Block;
import movement.Velocity;
import java.util.List;

/**
 * @author Nadav Elgrabli
 */
public interface LevelInformation {

    /**
     *
      * @return num of balls in level.
     */
    int numberOfBalls();

    /**
     *
      * @return list of ball velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return int paddle speed.
     */
    int paddleSpeed();

    /**
     *
     * @return int paddle width
     */
    int paddleWidth();

    /**
     *
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     *
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     *
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     *
     * @return int Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}