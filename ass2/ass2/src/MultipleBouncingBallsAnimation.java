/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;

/**
 * @author Nadav Elgrabli
 * Class creates multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    public static final int MAXIMUM_COORDINATE = 200;
    public static final int MAXIMUM_SPEED = 50;
    public static final int SAFETY_RANGE = 1;
    public static final int ANGLE_DEGREE = 360;
    public static final int MAX_SIZE_SPEED = 50;
    public static final int MAX_SCREEN_SIZE = 200;
    public static final int MAX_BALL_SIZE = 99;

    //Creating an array of the balls we create.
    private static Ball[] arrayOfBalls;

    /**
     * Method name: main
     * Input: array of strings..
     * Output: void.
     * Method operation: method receives array of strings, which are the different sizes of balls. It creates an
     * array of balls, and gives each ball a random initial position on screen, the given size from input and color
     * black. it makes sure that the balls do not exit the screen, and makes the speed of the ball slower as it
     * grows bigger.
     * @param args the arguments are the different sizes of balls we want to see bounce in array of strings.
     */
    public static void main(String[] args) {
        int numberOfBalls = args.length;
        arrayOfBalls = new Ball[args.length];
        Random rand = new Random();

        //Setting the max speed of the ball to 50.
        double maxSpeed = MAXIMUM_SPEED;

        //Using for loop to create each ball individually.
        for (int i = 0; i < numberOfBalls; i++) {

            // Assigning the initial x and y of the point of the ball randomly.
            Point position = new Point(rand.nextInt((MAX_SCREEN_SIZE) + SAFETY_RANGE),
                    rand.nextInt((MAX_SCREEN_SIZE) + SAFETY_RANGE));

            //Casting in on size of ball.
            int size = Integer.parseInt(args[i]);

            //if condition to make sure that ball size will fit inside the screen and won't be too big.
            if (size > MAX_BALL_SIZE) {
                size = MAX_BALL_SIZE;
            }
            //Creating the new ball with the new random position, and the given size, and color black.
            Ball ball = new Ball((int) position.getX(), (int) position.getY(), size,
                    java.awt.Color.BLACK);
            double centerX = position.getX();
            double centerY = position.getY();

            /*
            If the X coordinate of the center of our ball is smaller than the radius, we want the ball to appear
            inside its assigned width, so we change its X coordinate.
            */
            if (centerX <= ball.getSize()) {
                centerX = centerX + ball.getSize() + SAFETY_RANGE;
            }

            /*
            If the Y coordinate of the center of our ball is smaller than the radius, we want the ball to appear
            inside its assigned height, so we change its Y coordinate.
            */
            if (centerY <= ball.getSize()) {
                centerY = centerY + ball.getSize() + SAFETY_RANGE;
            }

            /*
            If the X coordinate of the center of our ball combined with he radius of the ball is outside of the
            defined width zone, we will want the X coordinate to begin at the end of the width so that it will not go
            out of our zone.
            */
            if (centerX + ball.getSize() >= MAXIMUM_COORDINATE) {
                centerX = centerX - ball.getSize() - SAFETY_RANGE;
            }

            /*
            If the Y coordinate of the center of our ball combined with he radius of the ball is outside of the
            defined height zone, we will want the Y coordinate to begin at the end of the height so that it will not go
            out of our zone.
             */
            if (centerY + ball.getSize() >= MAXIMUM_COORDINATE) {
                centerY = centerY - ball.getSize() - SAFETY_RANGE;
            }

            // Creating the new start point if its the radius is outside of its width or height.
            Point newPoint = new Point(centerX, centerY);

            //Making the velocity of the ball smaller as the ball gets bigger.
            double speed = maxSpeed / Math.min(size, MAX_SIZE_SPEED);

            //Giving the ball a random degree to start.
            double angle = rand.nextInt(ANGLE_DEGREE);

            //Setting the new velocity of the ball.
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ball.setVelocity(velocity);

            //use setCenter method to put the value of the new point in our current point.
            ball.setCenter(ball.getVelocity().applyToPoint(newPoint));

            //Add the ball to the array of balls.
            arrayOfBalls[i] = ball;
        }
        drawAnimation();
    }

    /**
     * Method name: drawAnimation.
     * Input: void.
     * Output: void.
     * Method operation: Draws the animation of the multiple bouncing balls. It uses two  loops to do so. First
     * while loop is to keep the animation going. Second for loop is to go over all the balls inside the array of
     * balls, and move and draw each of the simultaneously.
     */
    static private void drawAnimation() {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();

        //for loop to keep the animation running.
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            //for loop to move and draw each ball in the array of balls we previously created.
            for (int j = 0; j < arrayOfBalls.length; j++) {
                arrayOfBalls[j].moveOneStep();
                arrayOfBalls[j].drawOn(d);
            }

            //Show what we created.
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}

