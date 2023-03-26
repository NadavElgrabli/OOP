package game;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import geometry.Point;
import geometry.Rectangle;
import gui.Sprite;
import gui.collision.Block;
import movement.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nadav Elgrabli
 * Level informatioon of first elvel of game.
 */
public class DirectHit implements LevelInformation {

    //Constants for game window:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for paddle:
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_SPEED = 10;
    public static final int SPEED = 8;

    //Constants for balls:
    public static final int NUM_OF_BALLS = 1;
    public static final int INITIAL_ANGLE = 60;

    /**
     * method returns num of balls.
     * @return int num of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * Method returns list of velocities of balls.
     * @return list of velocity.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<Velocity>();
        int numberOfPairs = numberOfBalls() / 2;

        //if there are existing pairs, create their new velocity, with matching opposite angles.
        if (numberOfPairs != 0) {
            int dec = INITIAL_ANGLE / numberOfPairs;
            int angle = INITIAL_ANGLE;
            for (int i = 0; i < numberOfPairs; i++) {
                list.add(Velocity.fromAngleAndSpeed(angle, SPEED));
                list.add(Velocity.fromAngleAndSpeed(-angle, SPEED));
                angle = angle - dec;
            }
        }

        //If uneven number of balls, the middle ball will go upwards with angle of 0.
        if (numberOfBalls() % 2 == 1) {
            list.add(Velocity.fromAngleAndSpeed(0, SPEED));
        }
        return list;
    }

    /**
     *
     * @return int speed of paddle.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     *
     * @return int width of paddle
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     *
     * @return string name of level.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     *
     * @return sprite background of level.
     */
    @Override
    public Sprite getBackground() {
        return new Background(new Rectangle(new Point(0, 0) , WIDTH, HEIGHT), Color.BLACK);
    }

    /**
     *
     * @return list of blocks inside level
     */
    @Override
    public List<Block> blocks() {
        final int height = 150;
        ArrayList<Block> listOfBlocks = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point((WIDTH - EDGE_SIZE) / 2.0, height),
                EDGE_SIZE, EDGE_SIZE), Color.red);
        listOfBlocks.add(block);
        return listOfBlocks;
    }

    /**
     *
     * @return number of blocks needs to remove to finish level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
