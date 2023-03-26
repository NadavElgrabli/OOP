package gui; /******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import gui.collision.Collidable;
import gui.collision.CollisionInfo;
import gui.collision.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import movement.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Nadav Elgrabli
 * Class creates a ball, which has radius, center, color, velocity, and location on the screen. This class also
 * includes different menthods we will want to use on our balls.
 */
public class Ball implements Sprite {
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor name: GUI.Ball
     * Input: Geometry.Point center, int r, java.awt.Color color
     * Output: creates a ball.
     * Constructor operation: Creates a ball with given input.
     * @param center is the center coordinate of the ball - Geometry.Point
     * @param r is the radius of the ball - int
     * @param color is the color of the ball - java.awt.Color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.center = center;
    }

    /**
     * Constructor name: GUI.Ball.
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
    }

    /**
     * Constructor name: GUI.Ball.
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
    }

    /**
     * Constructor for GUI.Ball.
     * @param x x coordinate of center
     * @param y y coordinate of center
     * @param r radius of ball
     * @param color color of ball
     * @param gameEnvironment GUI.Collision.GameEnvironment of ball.
     * @param velocity velocity of ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment, Velocity velocity) {
        this.radius = r;
        this.color = color;
        this.center = new Point(x, y);
        this.gameEnvironment = gameEnvironment;
        this.velocity = velocity;
    }

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
     * Input: Geometry.Point.
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
     * Method name: drawOn.
     * Input: DrawSurface.
     * Output: void.
     * Method operation: draw the ball on the given DrawSurface
     * @param surface draw the ball on the given DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), radius);

        // draw the circumference of the ball
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Method name: setVelocity.
     * Input: Movement.Velocity.
     * Output: void.
     * Method operation: set velocity of ball.
     * @param v is the Movement.Velocity we set to ball.
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
     * Method name: moveOneStep
     * Method operation: method is in charge of moving the ball accordingly if the ball hits a surface.
     */
    public void moveOneStep() {
        //Ball hitter = new Ball(5,5,3, Color.RED);

        //Creating a new point if the ball hits a surface
        Point afterVelocityPoint = velocity.applyToPoint(center);

        //Creating the new line of trajectory.
        Line trajectory = new Line(center, afterVelocityPoint);

        //Go to game environment and get a point a collidable for the collisionInfo.
        CollisionInfo info = gameEnvironment.getClosestCollision(trajectory);

        // If moving in the trajectory doesn't hit anything, we move keep moving in the same direction.
        if (info == null) {
            center = velocity.applyToPoint(center);
        } else {
            Point collision = info.collisionPoint();

            // start from the collision point
            double x = collision.getX();
            double y = collision.getY();

            // move next to almost the collision point (according to the x/y axis direction)
            if (velocity.getDx() > 0) {
                x = x - 1;
            } else if (velocity.getDx() < 0) {
                x = x + 1;
            }
            if (velocity.getDy() > 0) {
                y = y - 1;
            } else if (velocity.getDy() < 0) {
                y = y + 1;
            }

            // notify the collidable object and update the velocity
            Collidable collidable = info.collisionObject();
            velocity = collidable.hit(this, collision, velocity);

            // move the ball
            center = new Point(x, y);
        }
    }

    /**
     * Set the game environment.
     * @param environment GUI.Collision.GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Add sprite to game.
     * @param g Game.Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove sprite from game.
     * @param g Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}