/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.*;
import java.util.Random;

/**
 * @author Nadav Elgrabli
 * Class creates multiple bouncing balls animation in different frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    public static final int MAXIMUM_SPEED = 50;
    public static final int SAFETY_RANGE = 1;
    public static final int ANGLE_DEGREE = 360;
    public static final int MAX_SIZE_SPEED = 50;
    public static final int MAX_SIZE_GRAY_FRAME = 224;
    public static final int MAX_SIZE_YELLOW_FRAME = 74;


    //Creates a new array of balls.
    private static Ball[] arrayOfBalls;

    /**
     * Method name: main.
     * Input: array of strings.
     * Output: void.
     * Method operation: method receives array of strings, which are the different sizes of balls. The balls are
     * divided to two frames, with equal amount of balls in each frame. The method creates an array of balls, and
     * gives each ball a random initial position on screen, the given size from input anda random color. it makes
     * sure that the balls do not exit the screen, and makes the speed of the ball slower as it grows bigger.
     * @param args the arguments are the different sizes of balls we want to see bounce in an array of strings.
     */
    public static void main(String[] args) {
        int numberOfBalls = args.length;

        //Creating an array of balls.
        arrayOfBalls = new Ball[args.length];
        Random rand = new Random();

        //Creating an array of colors that we want for our balls.
        Color[] colors = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.WHITE, Color.PINK };

        //Setting max speed of a ball to 50.
        double maxSpeed = MAXIMUM_SPEED;

        //for loop to create each ball.
        for (int i = 0; i < numberOfBalls; i++) {

            //Setting the first half of the balls to be in the frame of (50,50) to (500,500)
            int minLocation = 50;
            int maxLocation = 500;

            //if condition to set the second half of the balls to be in the frame of (450,450) to (600,600)
            if (i >= numberOfBalls / 2) {
                minLocation = 450;
                maxLocation = 600;
            }

            //Getting the size of the ball from the argument we were given.
            int size = Integer.parseInt(args[i]);

            //if condition to make sure that ball size will fit inside the gray frame and won't be too big.
            if (minLocation == 50){
                if (size > MAX_SIZE_GRAY_FRAME){
                    size = MAX_SIZE_GRAY_FRAME;
                }
            }

            //if condition to make sure that ball size will fit inside the yellow frame screen and won't be too big.
            if (minLocation == 450){
                if (size > MAX_SIZE_YELLOW_FRAME){
                    size = MAX_SIZE_YELLOW_FRAME;
                }
            }

            /*
            Setting the min position of the ball which is the minimum position he can start in. and setting the
            range position of the ball, which is the range the ball can move around in without exiting the frame.
             */
            int minPosition = minLocation + size + SAFETY_RANGE;
            int rangePosition = maxLocation - minLocation - 2 * size - SAFETY_RANGE;

            // initialize the balls inside the frame and make sure they don't cross or touch the edges.
            double centerX = rand.nextInt(rangePosition) + minPosition;
            double centerY = rand.nextInt(rangePosition) + minPosition;
            Point position = new Point(centerX, centerY);

            /*
            Creating the ball with the x,y coordinates, size, random color, and assigning the ball its min and max
            location, depending on which frame it is in.
             */
            Ball ball = new Ball((int) position.getX(), (int) position.getY(), size,
                    colors[i % colors.length], minLocation, maxLocation);

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
            if (centerX + ball.getSize() >= maxLocation) {
                centerX = centerX - ball.getSize() - SAFETY_RANGE;
            }

            /*
            If the Y coordinate of the center of our ball combined with he radius of the ball is outside of the
            defined height zone, we will want the Y coordinate to begin at the end of the height so that it will not go
            out of our zone.
             */
            if (centerY + ball.getSize() >= maxLocation) {
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
     * Method operation: Draws the animation of the multiple bouncing balls in different frames. It uses two
     * loops to do so. First while loop is to keep the animation going. Second for loop is to go over all the balls
     * inside the array of balls, and move and draw each of the simultaneously.
     */
    static private void drawAnimation() {
        GUI gui = new GUI("title", 700, 700);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            //Create the first grey Frame.
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);

            //Create the second yellow frame.
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);

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



