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
 * Level information of level 3.
 */
public class GreenThree implements LevelInformation {

    //Constants for game window:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for paddle:
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_SPEED = 10;

    //Constants for balls:
    public static final int NUM_OF_BALLS = 2;
    public static final int INITIAL_ANGLE = 60;
    public static final int SPEED = 8;


    //constants for block:
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 20;
    public static final int NUM_OF_BLOCKS_TOP_ROW = 10;
    public static final double FIRST_ROW_Y = 100;
    public static final double NUM_OF_ROWS_OF_BLOCKS = 5;

    /**
     *
     * @return int num of balls in level.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     *
     * @return list of velocities of balls in level.
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
     * @return int num of paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     *
     * @return int num of paddle width.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     *
     * @return string of name of level.
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     *
     * @return sprite of background of level.
     */
    @Override
    public Sprite getBackground() {
        Background backGround = new Background(new Rectangle(new Point(0, 0) , WIDTH, HEIGHT),
                Color.GREEN.darker());
        return backGround;    }

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
        double firstRowX = WIDTH - EDGE_SIZE - BLOCK_WIDTH * NUM_OF_BLOCKS_TOP_ROW;

        //Create array of colors for each row of blocks.
        Color[] rowColor = {Color.MAGENTA, Color.YELLOW, Color.RED, Color.BLUE, Color.PINK};


        //For loop to run on all the rows
        for (int i = 0; i < NUM_OF_ROWS_OF_BLOCKS; i++) {

            // get the first block's x-value for the ith row
            double startX = firstRowX + i * BLOCK_WIDTH;
            double startY = FIRST_ROW_Y + i * BLOCK_HEIGHT;

            //Number of blocks decrease by 1 in each new line
            int numOfBlocks = NUM_OF_BLOCKS_TOP_ROW - i;

            //Choose color for specific row
            Color color = rowColor[i % rowColor.length];

            // For loop to create each block in each row
            for (int j = 0; j < numOfBlocks; j++) {

                // create a new block and add it to the game
                double x = startX + j * BLOCK_WIDTH;
                geometry.Rectangle rect = new geometry.Rectangle(new Point(x, startY), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, color);
                listOfBlocks.add(block);
            }
        }
        return listOfBlocks;
    }

    /**
     *
     * @return int num of blocks to remove from level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
