/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class creates a line, using two points start and end. Class contains different methods that give us information
 * about a line, and constructors of the object Line.
 */

import java.util.List;

/**
 * @author Nadav Elgrabli
 * Class creates a line, using two points start and end. Class contains different methods that give us information
 * about a line, and constructors of the object Line.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * Constructor name: Line
     * Input: two Points, start and end
     * Output: void.
     * Constructor Operation: gives the start point and end point of the line.
     *
     * @param start is the start Point.
     * @param end   is the end Point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor name: Line
     * Input: 4 doubles of x and y coordinates of 2 points.
     * Output: void.
     *
     * @param x1 x coordinate of first point - double.
     * @param y1 y coordinate of first point - double.
     * @param x2 x coordinate of second point - double.
     * @param y2 y coordinate of second point - double.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Method name: length.
     * Input: void.
     * Output: double.
     * Method Operation: returns the length of the line using method distance.
     *
     * @return double that is the distance between 2 points.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Method name: middle.
     * Input: void.
     * Output: Point.
     * Method Operation: returns the middle point of the line.
     *
     * @return middle Point of the line.
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        Point middle = new Point(x, y);
        return middle;
    }

    /**
     * Method name: start
     * Input: void
     * Output: Point
     * Method operation: returns start point of the line.
     *
     * @return start point of line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Method name: end.
     * Input: void.
     * Output: Point.
     * Method operation: returns end point of line.
     *
     * @return end point of line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Method name: checkPointInLine
     * Input: Point
     * Output: True/False
     * Method operation: Checks if a point is in a line. The method checks that the distance of point 'p' from the
     * line's start point and from the line's end point and compares that distance to the distance from start point
     * to end point using the method 'checkEquals' to verify that two doubles are equal.
     *
     * @param p is the point we want to check that is on the line.
     * @return True / False. True if point on line, False if point not on line.
     */
    public boolean checkPointInLine(Point p) {
        return checkEquals(p.distance(start) + p.distance(end), start.distance(end));
    }

    /**
     * Method name: checkLineInLine
     * Input: Line
     * Output: True / False
     * Method operation: Checks if lines are coincident. If two lines are coincident, it means that the start point
     * and end point of one line is inside the second line. Therefore, we use method 'checkPointInLine'  to check
     * if both start and end points are in the line, if so, we return true, else, we return false.
     *
     * @param line is the line we receive.
     * @return true or false depending if lines coincident or not.
     */
    public boolean checkLineInLine(Line line) {

        // Using method checkPointInLine to check if both start and end points are in the line.
        return checkPointInLine(line.start) && checkPointInLine(line.end);
    }

    // Returns true if the lines intersect, false otherwise

    /**
     * Method name: isIntersecting.
     * Input: Line.
     * Output: True / False.
     * Method operation: Returns true if the lines intersect, false otherwise using method 'intersectionWith'
     *
     * @param other is the line we receive.
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Method name: checkEquals
     * Input: two doubles, firstNum and secondNum
     * Output: True / False
     * Method operation: Checks if two doubles are equal, by an accuracy of 12 numbers after the decimal
     * point.
     *
     * @param firstNum  first number we want to compare - double.
     * @param secondNum second number we want to compare - double.
     * @return true or false.
     */
    public boolean checkEquals(double firstNum, double secondNum) {

        // return the difference of the two numbers in absolute value
        double difference = Math.abs(firstNum - secondNum);

        /*
        EPSILON is a constant we will want to use to compare the difference with in order to check that the numbers
        are equal.
        */
        double epsilon = Math.pow(10, -12);
        return difference <= epsilon;
    }

    /**
     * Check if line is vertical.
     * @return TRUE if vertical, FALSE if not vertical.
     */
    public boolean isVertical() {
        return start.getX() == end.getX();
    }

    // Returns the intersection point if the lines intersect and null otherwise.

    /**
     * Method name: intersectionWith
     * Input: Line
     * Output: Point
     * Method operation: Method check if two lines intersect, if not, returns null. If lines indeed intersect,
     * returns the intersection point.
     *
     * @param other is the other line we want to check if out line intersects with.
     * @return Intersection point or null.
     */
    public Point intersectionWith(Line other) {

        //If the lines are just points, we return null.
        if (this.start().equals(this.end()) || (other.start().equals(other.end()))) {
            return null;
        }

        // Calculate the slopes of the first line and of the second line using: m = (y1-y2)/(x1-x2)
        double firstSlope = (this.start().getY() - this.end().getY()) / ((this.start().getX() - this.end().getX()));
        double secondSlope =
                (other.start().getY() - other.end().getY()) / ((other.start().getX()) - other.end().getX());

        /*
        If the slopes are equals there are several options. Either the lines are parallel and we will return null
        because they never intersect. Or the lines consolidate at one specific point, which means they continue
        each other, and we will return their intersection point.
         */
        if (checkEquals(firstSlope, secondSlope) || (isVertical() && other.isVertical())) {

            /*
            If the two lines intersect at the same start point, and are not inside each other, we will return the
            start point.
            */
            if (this.start().equals(other.start)) {
                if (!this.checkLineInLine(other) && !other.checkLineInLine(this)) {
                    return this.start();
                }
            } else if (this.end().equals(other.end)) {
                /*
                If the two lines intersect at the same end point, and are not inside each other, we will return the
                end point.
                */
                if (!this.checkLineInLine(other) && !other.checkLineInLine(this)) {
                    return this.end();
                }
            } else if (this.end().equals(other.start)) {
                /*
                If end point of the first line intersects the start point of the second line, and one line is not
                inside the other, we will return the intersection point.
                */
                if (!this.checkLineInLine(other) && !other.checkLineInLine(this)) {
                    return this.end();
                }
            } else if (this.start().equals(other.end)) {
                /*
                 If start point of the first line intersects the end point of the second line, and one line is not
                 inside the other, we will return the intersection point.
                */
                if (!this.checkLineInLine(other) && !other.checkLineInLine(this)) {
                    return this.start();
                }
            }
            // lines don't intersect when they have the same slope.
            return null;
        }

        Point intersectionPoint;
        if (isVertical()) {
            double x = start.getX();
            double secondYIntercept = other.start().getY() - secondSlope * other.start().getX();
            intersectionPoint = new Point(x, secondSlope * x + secondYIntercept);
        } else if (other.isVertical()) {
            double x = other.start.getX();
            double firstYIntercept = this.start().getY() - firstSlope * this.start().getX();
            intersectionPoint = new Point(x, firstSlope * x + firstYIntercept);
        } else {
            // Find the y intercept using simple math: (x = 0)    y - y1 = m(x-x1) -> y = y1 -mx1
            double firstYIntercept = this.start().getY() - firstSlope * this.start().getX();
            double secondYIntercept = other.start().getY() - secondSlope * other.start().getX();

            //Find x intercept of 2 lines: x = (c-b)/(m1-m2)
            double intersectionX = (secondYIntercept - firstYIntercept) / (firstSlope - secondSlope);
            double intersectionY = firstSlope * intersectionX + firstYIntercept;

            //Create new intersection point.
            intersectionPoint = new Point(intersectionX, intersectionY);
        }

        // if condition both lines contain the same intersection point, we return the intersection point.
        if (this.checkPointInLine(intersectionPoint) && other.checkPointInLine(intersectionPoint)) {
            return intersectionPoint;
        }

        // We didn't find an intersection point, therefore lines don't intersect.
        return null;
    }

    /**
     * Method name: equals.
     * Input: Line.
     * Output: True / False.
     * Method operation: return true is the lines are equal, false otherwise.
     *
     * @param other is the line we input.
     * @return True / false.
     */
    public boolean equals(Line other) {

        // if condition if the start and end points of the two lines equal, the lines are equals and return true.
        if (this.start().equals(other.start()) && this.end().equals(other.end())) {
            return true;
        }

        /*
        if the start point of the first line equals the end point of the second line, and the end point of the
        first line equals the start point of the second line, the lines are equal, and we return true.
         */
        if (this.start().equals(other.end()) && this.end().equals(other.start())) {
            return true;
        }

        // Lines are not equal, return false.
        return false;
    }

    /**
     * Method receives rectangle and returns the closest intersection point to the start of the line. If doesnt
     * intersect, returns null.
     * @param rect rectangle the method receives.
     * @return point / null depending if line intersects with rectangle.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //Create new list of intersection points (could be empty)
        List<Point> intersectionPointsList = rect.intersectionPoints(this);

        //if condition checks if the list is empty, then we return null.
        if (intersectionPointsList.isEmpty()) {
            return null;
        }

        // Assigning min distance to the first point of intersection with the start of the line.
        double minDistance = intersectionPointsList.get(0).distance(start);

        /*
        Assigning the closest point to start to the first intersection point to be able to change it in the
        for loop
        */
        Point closestPointToStart = intersectionPointsList.get(0);

        //for loop to find the real closest point of intersection to start
        for (int i = 0; i < intersectionPointsList.size(); i++) {

            /*
            'if' condition checks if the min distance is bigger than the distance between the next intersection
             point and the start of the line. If the minDistance is bigger, then we want the smaller distance to be
              the minDistance and save the intersection point of the new minDistance.
            */
            if (minDistance > intersectionPointsList.get(i).distance(start)) {
                minDistance = intersectionPointsList.get(i).distance(start);
                closestPointToStart = intersectionPointsList.get(i);
            }
        }
        return closestPointToStart;
    }

}