/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * Interface Collidable, consists of 2 methods.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity. The return is the new
     * velocity expected after the hit (based on the force the object inflicted on us).
     * @param collisionPoint point of collision.
     * @param currentVelocity velocity before the collision.
     * @return new velocity after hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}