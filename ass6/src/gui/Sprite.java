package gui; /******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import game.GameLevel;
import biuoop.DrawSurface;

/**
 * GUI.Sprite interface consists of 3 methods.
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
     * @param g Game.Game.
     */
    void addToGame(GameLevel g);
}
