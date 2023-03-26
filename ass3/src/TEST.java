import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/*import game.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import playables.Ball;
import playables.Block;*/

public class TEST {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // initialize all basic variables.
        GUI gui = new GUI("Game Window" , 800 , 600);
        GameEnvironment environment = new GameEnvironment();
        Block topBlock = new Block(new Rectangle(new Point(20, 0), 760, 40), Color.gray);
        Block downBlock = new Block(new Rectangle(new Point(20, 560), 760, 40), Color.gray);
        Block rightBlock = new Block(new Rectangle(new Point(0, 0), 40, 600), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(760, 0), 40, 600), Color.gray);
        environment.addCollidable(topBlock);
        environment.addCollidable(downBlock);
        environment.addCollidable(rightBlock);
        environment.addCollidable(leftBlock);

        Velocity velocity = new Velocity(5, 3);
        //Ball Creation.
        Ball newBall = new Ball(420, 350, 5, Color.black, environment, velocity);
        Ball newBall2 = new Ball(450, 350, 5, Color.black, environment, velocity);

        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            newBall.moveOneStep();
            newBall2.moveOneStep();
            topBlock.drawOn(d);
            downBlock.drawOn(d);
            rightBlock.drawOn(d);
            leftBlock.drawOn(d);
            newBall.drawOn(d);
            newBall2.drawOn(d);
            gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}