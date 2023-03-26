/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Nadav Elgrabli
 * Class creates a drawing of random generated lines, and colors the intersection points of the random lines in red
 * and the middle point of each random line in blue.
 */
public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final int POINT_RADIUS = 3;
    public static final int NUM_OF_LINES = 10;

    /**
     * Method name: drawRandomLines.
     * Input: void.
     * Output: void.
     * Method operation: creates a drawing of random generated lines, and colors the intersection points of the
     * random lines in red and the middle point of each random line in blue.
     */
    public void drawRandomLines() {

        // create a random-number generator
        Random rand = new Random();

        // Create array of lines we will use to restore our lines in.
        Line[] arrayOfLines;
        arrayOfLines = new Line[NUM_OF_LINES];

        // Create a window with the title "random Lines"
        GUI gui = new GUI("Random Lines Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();

        /*
        'for' loop to generate 10 random lines in color black, draw them, and insert them into our array of lines.
        Then we find the x and y coordinates of the middle point, and color the middle point in blue.
         */
        for (int i = 0; i < arrayOfLines.length; ++i) {

            //Creating two random generated (x1,y1), (x2, y2) and using these two point to create random line.
            int x1 = rand.nextInt(WIDTH) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(HEIGHT) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(WIDTH) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(HEIGHT) + 1; // get integer in range 1-300

            //Setting color to black.
            d.setColor(Color.BLACK);

            //Using drawLine to draw a line with he points we created..
            d.drawLine(x1, y1, x2, y2);

            // Creating a new line with the points we created.
            Line line = new Line(x1, y1, x2, y2);

            //Inserting the new line to out array of lines that we save.
            arrayOfLines[i] = line;

            //Finding the middle point of the line, and saving it's the x and y coordinate.
            Point middle = line.middle();
            int middleX = (int) middle.getX();
            int middleY = (int) middle.getY();

            //Setting color to blue.
            d.setColor(Color.BLUE);

            //Using 'fillCircle' to draw the middle point in blue.
            d.fillCircle(middleX, middleY, POINT_RADIUS);
        }

        /*
        for loop That checks if any of the random generated lines we created intersect with each other. If the
        intersect, we will color the intersection point in red and draw it.
         */
        for (int j = 0; j < arrayOfLines.length; ++j) {

            //Second for loop to make sure that all the lines check if they intersect with one another.
            for (int k = 0; k < arrayOfLines.length; ++k) {

                //if condition that means that the lines indeed intersect.
                if (arrayOfLines[j].intersectionWith(arrayOfLines[k]) != null) {

                    //Create the new intersection point.
                    Point intersectionPoint = arrayOfLines[j].intersectionWith(arrayOfLines[k]);

                    //Get the x and y coordinate of the intersection point.
                    int xintersection = (int) intersectionPoint.getX();
                    int yintersection = (int) intersectionPoint.getY();

                    // set color red
                    d.setColor(Color.RED);

                    //Draw the new intersection point in red.
                    d.fillCircle(xintersection, yintersection, POINT_RADIUS);
                }
            }
        }

        //Show what we created.
        gui.show(d);
    }

    /**
     * Method name: main
     * Input: arguments of strings
     * Output: void
     * Method operation: simply runs the drawRandomLines method we created in the main.
     * @param args strings of the input of main.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
