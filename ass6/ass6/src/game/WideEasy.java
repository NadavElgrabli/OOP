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
 * Level information of level 4.
 */
public class WideEasy implements LevelInformation {

    //Constants for game window:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for paddle:
    public static final int PADDLE_WIDTH = 700;
    public static final int PADDLE_SPEED = 10;

    //Constants for balls:
    public static final int NUM_OF_BALLS = 10;
    public static final int SPEED = 8;
    public static final int INITIAL_ANGLE = 60;

    //constants for block:
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 20;
    public static final int BLOCK_HEIGHT_LEVEL = 250;
    public static final int MAX_BLOCKS_IN_LINE = (WIDTH - 2 * EDGE_SIZE) / BLOCK_WIDTH;


    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> list = new ArrayList<Velocity>();
        int numberOfPairs = numberOfBalls() / 2;
        if (numberOfPairs != 0) {
            int dec = INITIAL_ANGLE / numberOfPairs;
            int angle = INITIAL_ANGLE;
            for (int i = 0; i < numberOfPairs; i++) {
                list.add(Velocity.fromAngleAndSpeed(angle, SPEED));
                list.add(Velocity.fromAngleAndSpeed(-angle, SPEED));
                angle = angle - dec;
            }
        }
        if (numberOfBalls() % 2 == 1) {
            list.add(Velocity.fromAngleAndSpeed(0, SPEED));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Rectangle(new Point(0, 0), WIDTH, HEIGHT), Color.WHITE);    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> listOfBlocks = new ArrayList<Block>();
        Color[] blockColor = {Color.MAGENTA, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.PINK};

        for (int i = 0; i < MAX_BLOCKS_IN_LINE; i++) {
            Rectangle rect = new Rectangle(new Point(EDGE_SIZE + (i * BLOCK_WIDTH), BLOCK_HEIGHT_LEVEL), BLOCK_WIDTH,
                    BLOCK_HEIGHT);

            //choose random color for block.
            Color color = blockColor[i % blockColor.length];

            //create block
            Block block = new Block(rect, color);
            listOfBlocks.add(block);

        }
        return listOfBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
