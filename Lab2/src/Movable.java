/**
 * @author      Lukas Gartman, Samuel Hammersberg, Kristoffer Gustafsson.
 * @version     1.0
 * @since       1.0
 */
public interface Movable {
    /**
     * Moves the card forward in its direction and speed.
     */
    void move();
    /**
     * Turns the car right.
     */
    void turnLeft();
    /**
     * Turns the car left.
     */
    void turnRight();
}
