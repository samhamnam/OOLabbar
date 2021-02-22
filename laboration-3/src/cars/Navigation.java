package cars;

import java.awt.geom.Point2D;

/**
 * A template for cars.Navigation subclasses.
 */
public abstract class Navigation {
    private double direction; // The current direction.
    private Point2D.Double position; // The current position.
    private final double turnRate; // How fast the object turns.

    /**
     * Creates a cars.Navigation instance from a position and rotation.
     * @param direction The direction the instance is in.
     * @param position The position the instance is in.
     */
    public Navigation(double direction, Point2D.Double position, double turnRate) {
        this.position = position;
        this.direction = direction;
        this.turnRate = turnRate;
    }

    /**
     * Creates a cars.Navigation instance with default options.
     */
    public Navigation() {
        this.position = new Point2D.Double(0,0);
        this.direction = 0;
        turnRate = 1/2.0;
    }
    /**
     * Gets the position.
     * @return Point2D.Double
     */
    public Point2D.Double getPosition() {
        return position;
    }
    /**
     * Returns the direction.
     * @return double
     */
    public double getDirection() { return direction * Math.PI; }

    /**
     * Sets the direction.
     * @param direction the new direction
     */
    public void setDirectionWithoutPI(double direction) {
        this.direction = direction;
    }

    /**
     * Sets the position
     * @param position the new position of the
     */
    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * Returns the turn rate.
     * @return double
     */
    public double getTurnRate() {
        return turnRate;
    }

    /**
     * Turns left.
     */
    public void turnLeft(){
        direction -= turnRate;
    }

    /**
     * Turns right.
     */
    public void turnRight(){
        direction += turnRate;
    }

    /**
     * Moves forward.
     */
    public void move() {
        getPosition().x += Math.cos(direction * Math.PI);
        getPosition().y += Math.sin(direction * Math.PI);
    }

    /**
     * Move in the current direction.
     * @param currentSpeed The speed to move at.
     */
    public void move(double currentSpeed) {
        getPosition().x += currentSpeed * Math.cos(direction * Math.PI);
        getPosition().y += currentSpeed * Math.sin(direction * Math.PI);
    }

}
