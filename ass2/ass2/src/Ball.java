/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import biuoop.DrawSurface;

/**
 * @author Nadav Elgrabli
 * Class creates a ball, which has radius, center, color, velocity, and location on the screen. This class also
 * includes different menthods we will want to use on our balls.
 */
public class Ball {
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity velocity;
    private int maxLocation;
    private int minLocation;

    public static final int MAX_SCREEN_LOCATION = 200;
    public static final int MIN_SCREEN_LOCATION = 0;
    public static final int SAFETY_RANGE = 1;




    /**
     * Constructor name: Ball
     * Input: Point center, int r, java.awt.Color color
     * Output: creates a ball.
     * Constructor operation: Creates a ball with given input.
     * @param center is the center coordinate of the ball - Point
     * @param r is the radius of the ball - int
     * @param color is the color of the ball - java.awt.Color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.center = new Point(center.getX(), center.getY());
        this.minLocation = MIN_SCREEN_LOCATION;
        this.maxLocation = MAX_SCREEN_LOCATION;
    }

    /**
     * Constructor name: Ball.
     * Input: int, and color.
     * Output: constructs a ball.
     * Constructor operation: Creates a ball with given input.
     * @param x int x coordinate of ball.
     * @param y int y coordinate of ball.
     * @param r int radius of ball.
     * @param color color of ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.center = new Point(x, y);
        this.minLocation = MIN_SCREEN_LOCATION;
        this.maxLocation = MAX_SCREEN_LOCATION;
    }

    /**
     * Constructor name: Ball.
     * Input: int, and color.
     * Output: constructs a ball.
     * Constructor operation: Creates a ball with given input.
     * @param x int x coordinate
     * @param y int y coordinate of ball.
     * @param r int r radius of ball.
     * @param color color of ball.
     * @param minLocation int min location on screen.
     * @param maxLocation int max location on screen.
     */
    public Ball(int x, int y, int r, java.awt.Color color, int minLocation, int maxLocation) {
        this.radius = r;
        this.color = color;
        this.center = new Point(x, y);
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
    }

    // accessors

    /**
     * Method name: getX.
     * Input: void.
     * Output: double.
     * Method operation: accessor to get x coordinate of ball.
     * @return x coordinate of center of ball - double.
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * Method name: getY.
     * Input: void.
     * Output: double.
     * Method operation: accessor to get y coordinate of ball.
     * @return y coordinate of center of ball - double.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * Method name: getSize.
     * Input: void.
     * Output: int.
     * Method operation: accessor to get radius of ball.
     * @return radius of ball - int.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Method name: setCenter.
     * Input: Point.
     * Output: void.
     * Method operation: set center of point to p.
     * @param p is the point we receive that we want to set our center to.
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * Method name: getColor.
     * Input: void.
     * Output: java.awt.Color.
     * Method operation: gets the color from our ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Method name: getMaxLocation.
     * Input: void.
     * Output: int.
     * Method operation: gets the max location of the ball.
     * @return int max location.
     */
    public int getMaxLocation() {
        return maxLocation;
    }

    /**
     * Method name: getMinLocation.
     * Input: void.
     * Output: int.
     * Method operation: gets the min location of the ball.
     * @return int min location.
     */
    public int getMinLocation() {
        return minLocation;
    }

    /**
     * Method name: setMaxLocation.
     * Input: int.
     * Output: void.
     * Method operation: set max location of ball.
     * @param max is the max location we want to set our ball in - int.
     */
    public void setMaxLocation(int max) {
        this.maxLocation = max;
    }

    /**
     * Method name: setMinLocation.
     * Input: int.
     * Output: void.
     * Method operation: set min location of ball.
     * @param min is the min location we want to set our ball in - int.
     */
    public void setMinLocation(int min) {
        this.minLocation = min;
    }

    /**
     * Method name: drawOn.
     * Input: DrawSurface.
     * Output: void.
     * Method operation: draw the ball on the given DrawSurface
     * @param surface draw the ball on the given DrawSurface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), radius);
    }

    /**
     * Method name: setVelocity.
     * Input: Velocity.
     * Output: void.
     * Method operation: set velocity of ball.
     * @param v is the Velocity we set to ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Method name: setVelocity.
     * Input: double dx, double dy.
     * Output: void.
     * Method operation: set velocity of ball by dx and dy.
     * @param dx velocity in x direction - double.
     * @param dy velocity in y direction - double.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Method name: getVelocity.
     * Input: void.
     * Output: void.
     * Method operation: accessor to get the velocity.
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Method name: moveOneStep.
     * Input: void.
     * Output: void.
     * Method operation: This method is in charge of physically moving the ball. This method moves the ball in its
     * designated direction, and makes sure it does not go beyond its confined maxLocation and minLocation. When it
     * hits the max/minLocation its velocity turns direction.
     */
    public void moveOneStep() {

        //Setting velocity to the ball.
        this.center = this.getVelocity().applyToPoint(this.center);

        //Creating variables of the coordinates of the center of the ball.
        double centerX = center.getX();
        double centerY = center.getY();

        //If the ball reaches the right edge of screen, we change direction to left.
        if (centerX + radius >= maxLocation) {
            double dx = velocity.getDx();

            //Changing to opposite x direction.
            dx = -dx;

            // Setting new velocity in correct direction.
            setVelocity(dx, velocity.getDy());

            //Making sure the centerX of ball doesn't get out of zone.
            centerX = maxLocation - radius - SAFETY_RANGE;
        }

        // If the ball reaches the left edge of screen, we change direction to the right.
        if (centerX - radius <= minLocation) {
            double dx = velocity.getDx();

            //Changing to opposite x direction.
            dx = -dx;

            // Setting new velocity in correct direction.
            setVelocity(dx, velocity.getDy());

            //Making sure the centerX of ball doesn't get out of zone.
            centerX = minLocation + radius + SAFETY_RANGE;
        }

        //If the ball reaches the bottom edge of screen, we change direction to the top.
        if (centerY + radius >= maxLocation) {
            double dy = velocity.getDy();

            //Changing to opposite y direction.
            dy = -dy;

            // Setting new velocity in correct direction.
            setVelocity(velocity.getDx(), dy);

            //Making sure the centerY of ball doesn't get out of zone.
            centerY = maxLocation - radius - SAFETY_RANGE;
        }

        //If the ball reaches the top edge of screen, we change direction to bottom.
        if (centerY - radius <= minLocation) {
            double dy = velocity.getDy();

            //Changing to opposite y direction.
            dy = -dy;

            // Setting new velocity in correct direction.
            setVelocity(velocity.getDx(), dy);

            //Making sure the centerY of ball doesn't get out of zone.
            centerY = minLocation + radius + SAFETY_RANGE;
        }

        // Setting new correct point as the center of the ball.
        this.center = new Point(centerX, centerY);
    }
}