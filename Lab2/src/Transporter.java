import org.junit.Ignore;

import java.awt.geom.Point2D;
public abstract class Transporter implements Movable {
    Navigation nav;

    /**
     * Creates a transporter.
     * @param nav The navigation instance.
     */
    Transporter(Navigation nav) {
        this.nav = nav;
    }

    private double currentSpeed; // The current speed of the transporter

    /**
     * Returns the speed of the transporter
     * @return double
     */
    double getCurrentSpeed(){
        return currentSpeed;
    }
    /**
     * Turns the transporter left.
     */
    public void turnLeft(){
        nav.turnLeft();
    }

    /**
     * Turns the transporter right.
     */
    public void turnRight(){
        nav.turnRight();
    }

    /**
     * Moves forward.
     */
    public void move(){
        nav.move(currentSpeed);
    }


    /**
     * Sets the speed of the transporter
     * @param amount: amount to set currentSpeed to.
     */
    void setCurrentSpeed(double amount) {
        this.currentSpeed = amount;
    }

    /**
     * Speed up the transporter.
     * @param amount: change amount.
     */
    void gas(double amount) {
        amount = clamp(amount, 0,1);
        incrementSpeed(amount);
    }

    /**
     * Speed down the transporter.
     * @param amount: change amount.
     */
    void brake(double amount) {
        amount = clamp(amount, 0, 1);
        decrementSpeed(amount);
    }

    /**
     * Clamps a value between two points.
     * @param x: value to be clamped.
     * @param min: minimum clamp value.
     * @param max: maximum clamp value.
     * @return double
     */
    static double clamp(double x, double min, double max) {
        if (x > max)
            x = max;
        else if (x < min)
            x = min;
        return x;
    }

    /**
     * How speed is calculated.
     */
    abstract double speedFactor();

    /**
     * Increment the speed.
     * @param amount: amount to increment by.
     */
    void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    /**
     * Decrement the speed.
     * @param amount: amount to decrement by.
     */
    void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

}
