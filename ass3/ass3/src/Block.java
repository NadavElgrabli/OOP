/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Nadav Elgrabli
 * Block class that is part of interfaces Collidable and Sprite.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor of block.
     * @param rectangle Rectangle of block.
     * @param color color of block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Method returns the rectangle of the block of the collision.
     * @return Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Method changes the velocity of the ball according to where the ball hits the block. if the ball hits a
     * vertical edge of the block, the horizontal direction should change, and if the ball hits an horizontal edge
     * of the block, the vertical direction should change
     * @param collisionPoint collision of the ball and the block.
     * @param currentVelocity current velocity of the ball.
     * @return new velocity of the ball.
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        //use method linesOfRectangle to get all the lines that form the rectangle in an array.
        Line[] rectangleLines = rectangle.linesOfRectangle();

        //Convenient constant names.
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        //hits upper line of rectangle, change vertical direction.
        if (rectangleLines[0].checkPointInLine(collisionPoint)) {
            dy = -dy;
        }
        //hits left line of rectangle, change horizontal direction.
        if (rectangleLines[1].checkPointInLine(collisionPoint)) {
            dx = -dx;
        }
        //hits bottom of rectangle, change vertical direction.
        if (rectangleLines[2].checkPointInLine(collisionPoint)) {
            dy = -dy;
        }
        // hits right line of rectangle, change horizontal direction.
        if (rectangleLines[3].checkPointInLine(collisionPoint)) {
            dx = -dx;
        }

        //return new velocity.
        return new Velocity(dx, dy);
    }

    /**
     * Draw the block.
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);

        //fill rectangle of block.
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());

        //set the surface of the block to black.
        d.setColor(Color.BLACK);

        //draw the block.
        d.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());
    }

    /**
     * notify the block that time has passed.
     */
    @Override
    public void timePassed() { }

    /**
     * Add collidable / sprite to game.
     * @param g Game.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
