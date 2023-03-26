/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.DrawSurface;

/**
 * Sprite interface consists of 3 methods.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add sprite to game.
     * @param g Game.
     */
    void addToGame(Game g);
}
