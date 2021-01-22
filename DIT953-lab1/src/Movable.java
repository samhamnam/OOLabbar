package src;

/**
 * @author      Lukas Gartman, Samuel Hammersberg, Kristoffer Gustafsson.
 * @version     1.0
 * @since       1.0
 */
public interface Movable {
    /**
     * Moves the card forward in its direction and speed.
     * @return void
     */
    void move();
    /**
     * Turns the car right.
     * @return void
     */
    void turnLeft();
    /**
     * Turns the car left.
     * @return void
     */
    void turnRight();
}
