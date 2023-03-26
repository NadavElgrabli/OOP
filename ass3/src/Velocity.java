/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * Class creates a Velocity that specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    public static final int RIGHT_ANGLE = 90;

    /**
     * Constructor name: Velocity.
     * Input: double dx, double dy.
     * Output: Creates velocity.
     * Constructor operation: Creates Velocity.
     * @param dx velocity on x axes.
     * @param dy velocity on y axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Method name: applyToPoint.
     * Input: Point
     * Output: Point
     * Method operation: Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     * @param p is the given Point
     * @return new Point
     */
    public Point applyToPoint(Point p) {
        double positionX = p.getX() + dx;
        double positionY = p.getY() + dy;
        return new Point(positionX, positionY);
    }

    /**
     * Method name: getDx.
     * Input: void.
     * Output: double.
     * Method operation: accessor to return dx - velocity of x axes.
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Method name: getDy.
     * Input: void.
     * Output: double.
     * Method operation: accessor to return dy - velocity of y axes.
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Method name: fromAngleAndSpeed.
     * Input: double angle, double speed.
     * Output: Velocity.
     * Method operation: Method creates velocity with a given angle, using simple trigonometry calculations.
     * @param angle is the angle of the velocity.
     * @param speed is the speed we want to set.
     * @return new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        //Convert angle to radians and add 90 because that's how java works.
        angle = Math.toRadians(angle + RIGHT_ANGLE);

        /*
        Use cos and sin to calculate dx and dy according to the angle, and multiply by -1 because its opposite
        direction in java.
        */
        double dx = speed * Math.cos(angle) * -1;
        double dy = speed * Math.sin(angle) * -1;
        return new Velocity(dx, dy);
    }

    /**
     * Set new dy to velocity.
     * @param newDy double dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * set new dx to velocity.
     * @param newDx double dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
}