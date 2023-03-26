import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.DirectHit;
import game.FinalFour;
import game.GreenThree;
import game.LevelInformation;
import game.WideEasy;
import game.AnimationRunner;
import java.util.ArrayList;
import java.util.List;

/******************************
 * Name: Nadav Elgrabli.
 * ID: 316082791
 *****************************/

public class Ass6Game {
    /**
     * Main class that runs game using initialize and run methods.
     * @param args arguments of string.
     */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SEC = 60;

    /**
     *
     * @param args string arguments of levels from user.
     */
    public static void main(String[] args) {

        //list of levels from the index of arguments.
        List<Integer> levelsIndexesList = new ArrayList<>();

        //list of levels
        List<LevelInformation> levels = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            try {
                if (1 <= Integer.parseInt(args[i]) && Integer.parseInt(args[i]) <= 4) {
                    levelsIndexesList.add(Integer.parseInt(args[i]));
                }
            } catch (Exception e) {
                int a = 5;
            }
        }
        //if no arguments, run the game with the 4 main levels by order.
        if (levelsIndexesList.size() == 0) {
            DirectHit levelOne = new DirectHit();
            levels.add(levelOne);
            WideEasy levelTwo = new WideEasy();
            levels.add(levelTwo);
            GreenThree levelThree = new GreenThree();
            levels.add(levelThree);
            FinalFour levelFour = new FinalFour();
            levels.add(levelFour);
        }
        //for loop to add all levels to level list
        for (int i = 0; i < levelsIndexesList.size(); i++) {
            if (levelsIndexesList.get(i) == 1) {
                DirectHit levelOne = new DirectHit();
                levels.add(levelOne);
            }
            if (levelsIndexesList.get(i) == 2) {
                WideEasy levelTwo = new WideEasy();
                levels.add(levelTwo);
            }
            if (levelsIndexesList.get(i) == 3) {
                GreenThree levelThree = new GreenThree();
                levels.add(levelThree);
            }
            if (levelsIndexesList.get(i) == 4) {
                FinalFour levelFour = new FinalFour();
                levels.add(levelFour);
            }
        }

        //Create the game with the levels we desire.
        GUI gui = new GUI("Game Window", WIDTH, HEIGHT);
        KeyboardSensor key = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui, FRAMES_PER_SEC);
        GameFlow gameFlow = new GameFlow(runner, key, gui);
        gameFlow.runLevels(levels);
        gui.close();
    }
}
