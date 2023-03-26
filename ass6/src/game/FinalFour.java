package game;

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
 * Level information of last level.
 */
public class FinalFour implements LevelInformation {

    //Constants for game window:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for paddle:
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_SPEED = 10;

    //Constants for balls:
    public static final int NUM_OF_BALLS = 3;
    public static final int INITIAL_ANGLE = 60;
    public static final int SPEED = 6;

    //constants for block:
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 20;
    public static final int NUM_OF_BLOCKS_ROW = 19;
    public static final double FIRST_ROW_Y = 100;
    public static final double NUM_OF_ROWS_OF_BLOCKS = 7;

    /**
     * Method returns num of balls in level.
     * @return int number of balls in level.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * method returns initial ball velocities.
     * @return list of velocities of balls.
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
     * @return int paddle width.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     *
     * @return string of level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     *
     * @return sprite background of game.
     */
    @Override
    public Sprite getBackground() {
        return new Background(new Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.CYAN.darker());    }

    /**
     *
     * @return list of blocks in level.
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> listOfBlocks = new ArrayList<Block>();

        /*
        pin the row to the right side of the screen (before the edge) (get the x-value of the first block in the
        first row)
         */
        double firstRowX = WIDTH - EDGE_SIZE - BLOCK_WIDTH * NUM_OF_BLOCKS_ROW;

        //Create array of colors for each row of blocks.
        Color[] rowColor = {Color.MAGENTA, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.PINK, Color.cyan};


        //For loop to run on all the rows
        for (int i = 0; i < NUM_OF_ROWS_OF_BLOCKS; i++) {

            // get the first block's x-value for the ith row
            double startY = FIRST_ROW_Y + i * BLOCK_HEIGHT;

            //Choose color for specific row
            Color color = rowColor[i % rowColor.length];

            // For loop to create each block in each row
            for (int j = 0; j < NUM_OF_BLOCKS_ROW; j++) {

                // create a new block and add it to the game
                double x = EDGE_SIZE + j * BLOCK_WIDTH;
                geometry.Rectangle rect = new geometry.Rectangle(new Point(x, startY), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, color);
                listOfBlocks.add(block);
            }
        }
        return listOfBlocks;
    }

    /**
     *
     * @return number of blocks to remove from game.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
