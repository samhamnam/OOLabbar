import java.awt.geom.Point2D;

public abstract class Navigation {
    private double direction; // The current direction the transporter is going in.
    private Point2D.Double position; // The current position of the transporter.

    /**
     * Creates a Navigation instance from a position and rotation.
     * @param direction The direction the instance is in.
     * @param position The position the instance is in.
     */
    Navigation(double direction, Point2D.Double position){
        this.position = position;
        this.direction = direction;
    }

    /**
     * Creates a Navigation unit with default options.
     */
    Navigation(){
        this.position = new Point2D.Double(0,0);
        this.direction = 0;
    }
    /**
     * Gets the position of the transporter.
     * @return Point2D.Double
     */
    Point2D.Double getPosition() {
        return position;
    }
    /**
     * Returns the direction.
     * @return double
     */
    double getDirection() {return direction;}

    /**
     * Sets the direction of the transporter
     * @param direction the new direction of the transporter
     */
    void setDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Sets the position of the transporter
     * @param position the new position of the
     */
    void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * Turns the transporter left.
     */
    public abstract void turnLeft();

    /**
     * Turns the transporter right.
     */
    public abstract void turnRight();
    /**
     * Move the transporter in the current direction.
     * @param currentSpeed The speed to move at.
     */
    public void move(double currentSpeed) {
        getPosition().x += currentSpeed * Math.cos(getDirection());
        getPosition().y += currentSpeed * Math.sin(getDirection());
    }

    /**
     * Moves forward.
     */
    public void move() {
        getPosition().x += Math.cos(getDirection());
        getPosition().y += Math.sin(getDirection());
    }

}
