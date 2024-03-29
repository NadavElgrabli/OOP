package gui;
/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import game.Game;
import geometry.Rectangle;
import hit.Counter;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author  Nadav Elgrabli
 * class that indicates the score of the player
 */
public class ScoreIndicator implements Sprite {
    private Counter gameScore;
    private Rectangle rectangle;
    private Color color;

    /**
     * constructor.
     * @param gameScore counter
     * @param rectangle rectangle of score indicator
     * @param color color of rectangle
     */
    public ScoreIndicator(Counter gameScore, Rectangle rectangle, Color color) {
        this.gameScore = gameScore;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * draws the score indicator.
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //draw rectangle
        d.setColor(color);

        //fill rectangle of block.
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight());

        //set the text color to black.
        d.setColor(Color.BLACK);

        //draw Text
        d.drawText((int) rectangle.getUpperLeft().getX() + 380, (int) rectangle.getUpperLeft().getY() + 15,
                "Score: " + gameScore.getValue(), 12);
    }

    /**
     * time passed, not used.
     */
    @Override
    public void timePassed() {
        return;
    }

    /**
     * add sprite to game.
     * @param g Game.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
