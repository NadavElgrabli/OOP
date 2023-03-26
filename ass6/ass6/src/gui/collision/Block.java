package gui.collision;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import gui.Ball;
import gui.Sprite;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import hit.HitListener;
import hit.HitNotifier;
import movement.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nadav Elgrabli
 * GUI.Collision.Block class that is part of interfaces GUI.Collision.Collidable and GUI.Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor of block.
     * @param rectangle Geometry.Rectangle of block.
     * @param color color of block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Method returns the rectangle of the block of the collision.
     * @return Geometry.Rectangle
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
     * @param hitter the ball that hits the block.
     * @return new velocity of the ball.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

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

        //Notify hitter.
        this.notifyHit(hitter);

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
     * @param g Game.Game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove sprite and collidable from game.
     * @param game Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notify listeners when the ball hits the block.
     * @param hitter ball
     */
    private void notifyHit(Ball hitter) {

        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add listener to list.
     * @param hl listener
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removes listener from list.
     * @param hl listener
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
