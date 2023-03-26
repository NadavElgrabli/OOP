/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Nadav Elgrabli
 * Class creates a bouncing ball animation.
 */
public class BouncingBallAnimation {
    public static final int MAXIMUM_COORDINATE = 200;

    /**
     * Method name: main
     * Input: strings.
     * Output: void.
     * Method operation: Receives 4 arguments, first two arguments are the initial location of the ball. the two
     * remaining arguments are the speed of the ball in the x axes and y axes.
     * @param args are 4 arguments, first two arguments are the initial location of the ball. the two
     * remaining arguments are the speed of the ball in the x axes and y axes.
     */
    public static void main(String[] args) {

        // ignore all arguments after the fourth one
        if (args.length >= 4) {
            drawAnimation(new Point(Double.parseDouble(args[0]), Double.parseDouble(args[1])),
                    Double.parseDouble(args[2]), Double.parseDouble(args[3]));
        }
    }

    /**
     * Method name: drawAnimation
     * Input: Point start, double dx, double dy.
     * Output: void.
     * Method operation: draws the animation of the bouncing ball.
     * @param start starting point of the ball on screen.
     * @param dx speed in x axes of ball - double.
     * @param dy speed in y axes of ball - double.
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();

        //Creating new ball with x and y coordinates, radius 10 and color black.
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 10, java.awt.Color.BLACK);
        double centerX = start.getX();
        double centerY = start.getY();

        /*
        If the X coordinate of the center of our ball is smaller than the radius, we want the ball to appear
        inside its assigned width, so we change its X coordinate.
        */
        if (centerX <= ball.getSize()) {
            centerX = centerX + ball.getSize() + 1;
        }

        /*
        If the Y coordinate of the center of our ball is smaller than the radius, we want the ball to appear
        inside its assigned height, so we change its Y coordinate.
        */
        if (centerY <= ball.getSize()) {
            centerY = centerY + ball.getSize() + 1;
        }

        /*
        If the X coordinate of the center of our ball combined with he radius of the ball is outside of the
        defined width zone, we will want the X coordinate to begin at the end of the width so that it will not go
        out of our zone.
         */
        if (centerX + ball.getSize() >= MAXIMUM_COORDINATE) {
            centerX = centerX - ball.getSize() - 1;
        }

        /*
        If the Y coordinate of the center of our ball combined with he radius of the ball is outside of the
        defined height zone, we will want the Y coordinate to begin at the end of the height so that it will not go
        out of our zone.
         */
        if (centerY + ball.getSize() >= MAXIMUM_COORDINATE) {
            centerY = centerY - ball.getSize() - 1;
        }

        // Creating the new start point if the radius is outside of its width or height of screen.
        Point newPoint = new Point(centerX, centerY);
        ball.setVelocity(dx, dy);

        //use setCenter method to put the value of the new point in our current point.
        ball.setCenter(ball.getVelocity().applyToPoint(newPoint));

        //While loop to keep our ball moving.
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}