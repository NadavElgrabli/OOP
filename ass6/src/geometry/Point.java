package geometry; /******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class creates a point, using (x,y) coordinates. Class contains different methods that give us information about
 * given points, and constructor of the object Geometry.Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor Name: Geometry.Point
     * Input: double x, double y
     * Output: double x, double y
     * Constructor operation: constructs point with given x,y coordinates.
     * @param x is double x coordinate
     * @param y is double y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method name: distance
     * Input: Geometry.Point
     * Output: double
     * Method Operation: Method calculates distance between two points, using Math.sqrt library and basic math
     * calculation.
     * @param other is the other Geometry.Point.
     * @return the distance of this point to the other point - double.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX())
                + (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * Method Name: equals
     * Input: Geometry.Point
     * Output: True / False
     * Method Operation: Method gets a point, and checks if the x and y coordinates are equals to the other.x
     * and other.y coordinates. Returns true is the points are equal, false otherwise
     * @param other is the other Geometry.Point we compare coordinates with.
     * @return True or false, depending on result.
     */
    public boolean equals(Point other) {

        // if condition to check that we have a second point for comparison
        if (other != null) {

            // If both x and y coordinates match, return true.
            if (this.x == other.getX() && this.y == other.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method Name: getX
     * Input: void
     * Output: double
     * Method Operation: returns the X coordinate of the point.
     * @return X coordinate of the point - double.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Method Name: getY
     * Input: void
     * Output: double
     * Method Operation: returns the Y coordinate of the point.
     * @return Y coordinate of the point - double.
     */
    public double getY() {
        return this.y;
    }
}



