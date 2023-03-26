/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of SpriteCollection. Class in charge of methods of the sprites we want to add to the game.
 */
public class SpriteCollection {

    // Create list of sprites
    private List<Sprite> spritesList = new ArrayList<>();

    /**
     * add a new sprite to the list (add of list).
     * @param s Sprite.
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {

        // for loop on all sprite in spriteList.
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        // 'for' loop to draw all sprite and spritesList.
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).drawOn(d);
        }
    }
}