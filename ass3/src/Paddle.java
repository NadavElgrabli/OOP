/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Nadav Elgrabli
 * Paddle object is the rectangle the player controls.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;

    //speed constant.
    public static final int SPEED = 5;

    //Constants for game window.
    public static final int EDGE_SIZE = 20;
    public static final int WIDTH = 800;

    /**
     * Constructor of paddle.
     * @param keyboard KeyboardSensor being pressed.
     * @param rectangle rectangle of paddle.
     * @param color color of paddle.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Method in charge of moving the paddle to the left.
     */
    public void moveLeft() {

        //if condition to check that we wont move the paddle outside of the edge of screen.
        if (this.rectangle.getUpperLeft().getX() - SPEED >= EDGE_SIZE) {

            //Set the upper left point of the rectangle to -5;
            this.rectangle.setUpperLeft(-SPEED);
        }
    }

    /**
     * Method in charge of moving the paddle to the right.
     */
    public void moveRight() {

        //if condition to check that we wont move the paddle outside of the edge of screen.
        if (this.rectangle.getUpperLeft().getX() + SPEED <= WIDTH - EDGE_SIZE - rectangle.getWidth()) {

            //Set the upper left point of the rectangle to +5;
            this.rectangle.setUpperLeft(SPEED);
        }
    }

    /**
     * Method checks if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the rectangle of the paddle.
     * @param d DrawSurface.
     */
    public void drawOn(DrawSurface d) {

        //Get the needed information to create paddle, upper left point, width, height and wanted color.
        d.setColor(color);
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.fillRectangle(x, y, width, height);
    }

    /**
     * returns the collision rectangle.
     * @return Rectangle of collision.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Method finds the collision region of the paddle. Each collision region has a different effect on the angle
     * of the ball.
     * @param collisionPoint Point of collision.
     * @return int (1-5) that says in which region of the paddle the ball hit.
     */
    public int findCollisionRegion(Point collisionPoint) {

        //Decide the region of the paddle to 5.
        double regionSize = rectangle.getWidth() / 5;

        //comfortable constant names we will use.
        double upperLeftPaddleX = rectangle.getUpperLeft().getX();
        double collisionX = collisionPoint.getX();

        //Creating the int 'i' outside of the for loop so we can return it (the 'i' is our region)
        int i;

        //for loop to go over all the regions of the paddle.
        for (i = 1; i <= 4; i++) {

            //if condition to check in which region the collision takes place.
            if (collisionX < upperLeftPaddleX + i * regionSize) {
                break;
            }
        }

        //return the collision region.
        return i;
    }

    /**
     * Method is in charge of changing the velocity of the ball according the where the ball hit the paddle. Each
     * region of the paddle has a different effect on the velocity of the ball.
     * @param collisionPoint point of collision.
     * @param currentVelocity velocity before the collision.
     * @return new velocity after the collision.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);

        //Use method findCollisionRegion to find the region the ball hit the paddle in.
        int region = findCollisionRegion(collisionPoint);

        //switch case to address the effect on velocity by hitting each region.
        switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, speed);
            case 2:
                return Velocity.fromAngleAndSpeed(330, speed);
            case 3:
                return new Velocity(dx, -dy);
            case 4:
                return Velocity.fromAngleAndSpeed(30, speed);
            default:
                return Velocity.fromAngleAndSpeed(60, speed);
        }
    }

    /**
     * Add paddle to the game.
     * @param g Game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}