package game;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.KeyboardSensor;
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
import gui.SpriteCollection;
import gui.Sprite;
import gui.Ball;
import hit.Counter;
import gui.ScoreIndicator;

/**
 * @author Nadav Elgrabli
 * Game.Game class hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private boolean shouldCount = true;
    private LevelInformation levelInformation;


    //Constants for game window:
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int EDGE_SIZE = 20;

    //constants for countdown animation:
    public static final int FRAMES_PER_SEC = 60;
    public static final int COUNT_FROM = 3;
    public static final int NUM_OF_SECONDS = 2;

    //constants for paddle:
    public static final int PADDLE_HEIGHT = 10;
    public static final double TWO = 2.0;

    /**
     * Constructor.
     * @param gui  gui
     * @param levelInformation level information
     * @param score score counter.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, Counter score) {
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.score = score;
    }

    /**
     * Add collidable to the environment.
     *
     * @param c GUI.Collision.Collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove collidable from environment.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Add sprite to the sprite collection we created.
     *
     * @param s GUI.Sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove sprite from collection.
     *
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

        //Create the game window.
        //gui = new GUI("Game Window", WIDTH, HEIGHT);

        //Create rectangle of score board
        Rectangle scoreRect = new Rectangle(new Point(0, 0),
                WIDTH, EDGE_SIZE);
        ScoreIndicator scoreIndicator = new ScoreIndicator(levelInformation.levelName(), score, scoreRect,
                Color.LIGHT_GRAY);
        scoreIndicator.addToGame(this);

        //Get x and y coordinates of rectangle of paddle.
        final double paddleX = (WIDTH - levelInformation.paddleWidth()) / TWO;
        final double paddleY = HEIGHT - EDGE_SIZE - PADDLE_HEIGHT;

        //Create rectangle of paddle.
        Rectangle rect = new Rectangle(
                new Point(paddleX, paddleY), levelInformation.paddleWidth(), PADDLE_HEIGHT);

        //Create paddle.
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), rect, Color.ORANGE, levelInformation.paddleSpeed());

        //Add paddle to game
        paddle.addToGame(this);

        final double centerX = paddleX + levelInformation.paddleWidth() / 2.0;
        final int radius = 5;
        for (Velocity vel : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(centerX, paddleY - 5, radius, Color.WHITE, environment, vel);
            ball.addToGame(this);
        }
        remainingBalls.increase(levelInformation.numberOfBalls());

        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener trackScore = new ScoreTrackingListener(score);
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(trackScore);
        }
        remainingBlocks.increase(levelInformation.numberOfBlocksToRemove());

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
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        runner = new AnimationRunner(gui, FRAMES_PER_SEC);
        //this.createBallsOnTopOfPaddle(); // or a similar method

        this.running = true;

        //use our runner to run the current animation -- which is one turn of  the game.
        this.runner.run(this);
    }

    /**
     * draw one frame.
     * @param d drawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        // Starts the animation countdown as long as shouldCount is true.
        if (shouldCount) {
            this.runner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, sprites,
                    1000 / FRAMES_PER_SEC));
            shouldCount = false;
        }

        levelInformation.getBackground().drawOn(d);

        // the logic from the previous run method goes here.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // if no more balls end game.
        if (remainingBalls.getValue() == 0) {
            this.running = false;
        }

        //if no more blocks end game
        if (remainingBlocks.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }

        //pause the game by pressing 'p'.
        keyboard = this.gui.getKeyboardSensor();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     *
     * @return boolean.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     *
     * @return int num of remaining blocks.
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }

    /**
     *
     * @return int num of remaining balls in level.
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }
}