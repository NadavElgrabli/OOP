/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

/**
 * @author Nadav Elgrabli
 * CollisionInfo class is a class of collidable objects and their collision point.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructor of CollisionInfo.
     * @param p Point of collision
     * @param c Collidable type.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collidableObject = c;
    }

    /**
     * Get the point at which the collision occurs.
     * @return collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Get the collidable object involved in the collision.
     * @return collidable object.
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}