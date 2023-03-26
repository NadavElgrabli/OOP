package game;

import biuoop.DrawSurface;
import geometry.Rectangle;
import gui.Sprite;
import java.awt.Color;

/**
 * @author Nadav Elgrabli
 * Background class of each level.
 */
public class Background implements Sprite {
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param rect rectangle
     * @param color color
     */
    public Background(Rectangle rect, Color color) {
        this.color = color;
        this.rectangle = rect;
    }

    /**
     * draw method.
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
     * time passed method - unused.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add background to game method.
     * @param g Game.Game.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
