package geometry; /******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nadav Elgrabli
 * Class of rectangle. Consists of methods that give us information about the rectangle and constructor of rectangle.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeftPoint;
    private static Line[] arrayOfLines;
    private static Line[] rectangleLines;

    public static final int NUMBER_OF_LINES_IN_RECTANGLE = 4;

    /**
     * Constructor of rectangle.
     * @param upperLeft upper left point of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Method operation: Method creates an array that consists of all the lines that form the rectangle and returns
     * it.
     * @return array of lines of the rectangle.
     */
    public Line[] linesOfRectangle() {

        //Create the array we will store the lines in.
        arrayOfLines = new Line[NUMBER_OF_LINES_IN_RECTANGLE];

        //Top right point of rectangle
        double x2 = upperLeftPoint.getX() + width;
        double y2 = upperLeftPoint.getY();

        //Bottom left point of rectangle
        double x3 = upperLeftPoint.getX();
        double y3 = upperLeftPoint.getY() + height;

        //Bottom right pint of rectangle.
        double x4 = upperLeftPoint.getX() + width;
        double y4 = upperLeftPoint.getY() + height;

        //Create top upper line of rectangle and add to array.
        Line upperLine = new Line(upperLeftPoint.getX(), upperLeftPoint.getY(), x2, y2);
        arrayOfLines[0] = upperLine;

        //Create top left line of rectangle and add to array.
        Line leftLine = new Line(upperLeftPoint.getX(), upperLeftPoint.getY(), x3, y3);
        arrayOfLines[1] = leftLine;

        //Create top bottom line of rectangle and add to array.
        Line bottomLine = new Line(x3, y3, x4, y4);
        arrayOfLines[2] = bottomLine;

        //Create top right line of rectangle and add to array.
        Line rightLine = new Line(x2, y2, x4, y4);
        arrayOfLines[3] = rightLine;

        //return array.
        return arrayOfLines;
    }

    /**
     * Method gets a specified line and returns a (possibly empty) List of intersection points.
     * @param line line it receives
     * @return list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        //Create new list we will store intersection points in.
        List<Point> intersectionPointsList = new ArrayList<>();

        // Creating a new array that will contain the lines of the rectangle
        rectangleLines = linesOfRectangle();

        for (int i = 0; i < rectangleLines.length; i++) {
            //If the 'if' condition returns not null, it means there is an intersection point we want to add to list
            if (line.intersectionWith(rectangleLines[i]) != null) {
                //Add intersection point to list.
                intersectionPointsList.add(line.intersectionWith(rectangleLines[i]));
            }
        }

        //return the intersection point list.
        return intersectionPointsList;
    }

    /**
     * Get width fo rectangle.
     * @return return width of rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height of rectangle.
     * @return return height of rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point of rectangle.
     * @return return upper left point of rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeftPoint;
    }

    /**
     * Set new upper left point of rectangle.
     * @param x double x we want to add to x coordinate of upper left point.
     * @return new upper left point.
     */
    public Point setUpperLeft(double x) {
        upperLeftPoint = new Point(upperLeftPoint.getX() + x, upperLeftPoint.getY());
       return upperLeftPoint;
    }
}