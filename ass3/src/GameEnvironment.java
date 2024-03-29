/******************************
 * Name: Nadav Elgrabli
 * ID: 316082791
 *****************************/
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nadav Elgrabli
 * The collection of objects the ball can collide with.
 */
public class GameEnvironment {

    //Creating new list of collidable objects.
    private List<Collidable> collidableObjectsList = new ArrayList<>();

    /**
     * add the given collidable to the environment.
     * @param c Collidable.
     */
    public void addCollidable(Collidable c) {
        collidableObjectsList.add(c);
    }

    /**
     * Method returns the information about the closest collision that is going to occur between trajectory and
     * CollisionInfo. If there is no collision, returns null.
     * @param trajectory Line the method receives.
     * @return closest CollisionInfo to intersection point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        //Use method listOfCollisionInfo to get list of all CollisionInfo.
        List<CollisionInfo> listOfCollisions = listOfCollisionInfo(trajectory);

        //return null if list is empty.
        if (listOfCollisions.isEmpty()) {
            return null;
        }

        /*
        If there are collisions, find the smallest distance between this.start and collision point and return
        collision info of the closest point to start of line.
        Set the closest CollisionInfo to start of line as null.
         */
        CollisionInfo info = null;

        //Set current min distance to be the distance between the first CollisionInfo and the trajectory.start
        double minDistance = listOfCollisions.get(0).collisionPoint().distance(trajectory.start());

        /*
        'for' loop to go over listOfCollisions and find the real minDistance between this.start and collision
         point. Return collision info of the closest point to start of line.
        */
        for (int i = 0; i < listOfCollisions.size(); i++) {
            if (minDistance >= listOfCollisions.get(i).collisionPoint().distance(trajectory.start())) {
                minDistance = listOfCollisions.get(i).collisionPoint().distance(trajectory.start());

                //Set info the be the CollisionInfo with the real minDistance.
                info = listOfCollisions.get(i);
            }
        }
        return info;
    }

    /**
     * Create list of collision info that intersects with line. if no intersections exists then return an empty list.
     * @param trajectory Line the method receives.
     * @return a (possibly empty) list of CollisionInfo.
     */
    public List<CollisionInfo> listOfCollisionInfo(Line trajectory) {

        //Create the new list of collisionInfo that we want.
        List<CollisionInfo> collisionInfoList = new ArrayList<>();

        //for loop to go overall objects collidable list and check if they intersect with the trajectory.
        for (int i = 0; i < collidableObjectsList.size(); i++) {

            //Assign point p to be the closest collision point with the rectangle.
            Point p = trajectory.closestIntersectionToStartOfLine(
                    collidableObjectsList.get(i).getCollisionRectangle());

            //If p is not not, add the CollisionInfo to the list of CollisionInfo.
            if (p != null) {
                collisionInfoList.add(new CollisionInfo(p, collidableObjectsList.get(i)));
            }
        }

        // Return the CollisionInfo list.
        return collisionInfoList;
    }
}