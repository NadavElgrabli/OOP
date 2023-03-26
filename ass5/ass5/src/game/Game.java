package game;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.collision.Block;
import gui.collision.Collidable;
import gui.collision.GameEnvironment;
import gui.collision.Paddle;
import geometry.Point;
import geometry.Rectangle;
import hit.ScoreTrackingListener;
import hit.BallRemover;
import hit.BlockRemover;
import movement.Velocity;
import biuoop.GUI;
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.SpriteCollection;
import gui.Sprite;
import gui.Ball;
import hit.Counter;
import gui.ScoreIndicator;

/**
 * @author Nadav Elgrabli
 * Game.Game class hold the sprites and the collidables, and will be in charge of the animation.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;

    //Constants for game window.
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for paddle:
    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 10;
    public static final double TWO = 2.0;

    //constants for block:
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 20;
    public static final int NUM_OF_ROWS_OF_BLOCKS = 5;
    public static final int NUM_OF_BLOCKS_TOP_ROW = 12;
    public static final double FIRST_ROW_Y = 100;

    //Constants for balls:
    public static final int FIRST_BALL_X_SPEED = 4;
    public static final int FIRST_BALL_Y_SPEED = 3;
    public static final int FIRST_BALL_RADIUS = 5;
    public static final int SECOND_BALL_X_SPEED = 6;
    public static final int SECOND_BALL_Y_SPEED = 6;
    public static final int SECOND_BALL_RADIUS = 7;
    public static final int THIRD_BALL_X_SPEED = 2;
    public static final int THIRD_BALL_Y_SPEED = 7;
    public static final int THIRD_BALL_RADIUS = 5;

    /**
     * Constructor.
     */
    public Game() {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
    }

    /**
     * Add collidable to the environment.
     * @param c GUI.Collision.Collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove collidable from environment.
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add sprite to the sprite collection we created.
     * @param s GUI.Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite from collection.
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Method Operation: Method initializes the game, it physically creates the game window, balls, blocks, paddle.
     */
    public void initialize() {
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        score = new Counter();

        //Create the game window.
        gui = new GUI("Game.Game Window" , WIDTH , HEIGHT);

        //Create rectangle of score board
        Rectangle scoreRect = new Rectangle(new Point(0, 0),
        WIDTH, EDGE_SIZE);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, scoreRect, Color.LIGHT_GRAY);
        scoreIndicator.addToGame(this);

        //Movement.Velocity creation of balls.
        Velocity velocityOfFirstBall = new Velocity(FIRST_BALL_X_SPEED, FIRST_BALL_Y_SPEED);
        Velocity velocityOfSecondBall = new Velocity(SECOND_BALL_X_SPEED, SECOND_BALL_Y_SPEED);
        Velocity velocityOfThirdBall = new Velocity(THIRD_BALL_X_SPEED, THIRD_BALL_Y_SPEED);

        //first ball creation:
        Ball firstBall = new Ball(420, 350, FIRST_BALL_RADIUS, Color.black, environment, velocityOfFirstBall);
        firstBall.setGameEnvironment(environment);
        firstBall.addToGame(this);
        remainingBalls.increase(1);

        //Second ball creation:
        Ball secondBall = new Ball(400, 350, SECOND_BALL_RADIUS, Color.red, environment, velocityOfSecondBall);
        secondBall.setGameEnvironment(environment);
        secondBall.addToGame(this);
        remainingBalls.increase(1);

        //Third ball creation:
        Ball thirdBall = new Ball(400, 350, THIRD_BALL_RADIUS, Color.blue, environment, velocityOfThirdBall);
        thirdBall.setGameEnvironment(environment);
        thirdBall.addToGame(this);
        remainingBalls.increase(1);

        /*
        pin the row to the right side of the screen (before the edge) (get the x-value of the first block in the
        first row)
         */
        double firstRowX = WIDTH - EDGE_SIZE - BLOCK_WIDTH * NUM_OF_BLOCKS_TOP_ROW;

        //Create array of colors for each row of blocks.
        Color[] rowColor = {Color.MAGENTA, Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.PINK};

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
                Rectangle rect = new Rectangle(new Point(x, startY), BLOCK_WIDTH, BLOCK_HEIGHT);
                Block block = new Block(rect, color);
                block.addToGame(this);

                //Add block to counter;
                remainingBlocks.increase(1);

                //Add tracking score listener.
                ScoreTrackingListener trackScore = new ScoreTrackingListener(score);
                block.addHitListener(trackScore);

                //Create new blockRemover, and add it as listener.
                BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
                block.addHitListener(blockRemover);
            }
        }

        //Create the blocks in the edge of the game window, that block the ball from leaving the screen
        Block topBlock = new Block(new Rectangle(new Point(EDGE_SIZE, EDGE_SIZE),
                WIDTH - EDGE_SIZE, EDGE_SIZE), Color.gray);

        //Make the downBlock slightly under the edge of the screen.
        Block downBlock = new Block(new Rectangle(new Point(EDGE_SIZE, HEIGHT - EDGE_SIZE + EDGE_SIZE),
                WIDTH - EDGE_SIZE,
                EDGE_SIZE), Color.gray);

        //Create ballRemover and add it as listener to downBlock.
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        downBlock.addHitListener(ballRemover);
        Block rightBlock = new Block(new Rectangle(new Point(0, EDGE_SIZE), EDGE_SIZE, HEIGHT), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(WIDTH - EDGE_SIZE, EDGE_SIZE),
                EDGE_SIZE, HEIGHT), Color.gray);

        //Add blocks to game
        topBlock.addToGame(this);
        downBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);

        //Get x and y coordinates of rectangle of paddle.
        final double paddleX = (WIDTH - PADDLE_WIDTH) / TWO;
        final double paddleY = HEIGHT - EDGE_SIZE - PADDLE_HEIGHT;

        //Create rectangle of paddle.
        Rectangle rect = new Rectangle(
                new Point(paddleX, paddleY), PADDLE_WIDTH, PADDLE_HEIGHT);

        //Create paddle.
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), rect, Color.black);

        //Add paddle to game
        paddle.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        //Create new sleeper.
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        //'while' loop to keep the game running.
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            //draw all sprites.
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            // if no more balls end game.
            if (remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }

            //if no more blocks end game
            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }
        }
    }
}
