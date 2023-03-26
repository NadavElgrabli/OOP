package gui.collision; /******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/

import geometry.Point;

/**
 * @author Nadav Elgrabli
 * GUI.Collision.CollisionInfo class is a class of collidable objects and their collision point.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Constructor of GUI.Collision.CollisionInfo.
     * @param p Geometry.Point of collision
     * @param c GUI.Collision.Collidable type.
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