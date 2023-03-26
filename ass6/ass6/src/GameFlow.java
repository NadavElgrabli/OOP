import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.Animation;
import game.AnimationRunner;
import game.GameLevel;
import game.KeyPressStoppableAnimation;
import hit.Counter;
import game.EndScreen;
import game.LevelInformation;
import java.util.List;

/**
 * @author Nadav Elgrabli
 * Gameflow class in charge  for moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keySensor;
    private GUI gui;

    /**
     * Constructor.
     * @param runner animation runner.
     * @param ks key sensor.
     * @param gui gui.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor ks, GUI gui) {
        this.runner = runner;
        this.keySensor = ks;
        this.gui = gui;

    }

    /**
     * Method that runs all the levels. Also stops the run if no more balls.
     * @param levels levels of game.
     */
    public void runLevels(List<LevelInformation> levels) {

        //create ne counter to keep track of score.
        Counter score = new Counter();

        //Assume player wins.
        boolean playerWon = true;

        //Run on list of levels.
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui, score);
            level.initialize();

            //while level has more blocks and balls, keep running.
            while (level.getRemainingBlocks() != 0 && level.getRemainingBalls() != 0) {
                level.run();
            }

            //if no more balls left, player loses.
            if (level.getRemainingBalls() == 0) {

                //update playWon to false.
                playerWon = false;
                break;
            }

        }

        //Create new animation screen.
        Animation screen;
        if (playerWon) {
            screen = new KeyPressStoppableAnimation(keySensor, KeyboardSensor.SPACE_KEY,
                    new EndScreen("You Win! Your score is " + score.getValue()));
        } else {
            screen = new KeyPressStoppableAnimation(keySensor, KeyboardSensor.SPACE_KEY,
                    new EndScreen("Game Over! Your score is " + score.getValue()));
        }
        runner.run(screen);
    }
}