package cars;

import org.junit.Ignore;

/**
 * A basic concept of transportation
 */
public abstract class Transporter implements Movable {
    public Navigation nav;
    private double currentSpeed; // The current speed of the transporter
    /**
     * Creates a transporter.
     * @param nav The navigation instance.
     */
    public Transporter(Navigation nav) {
        this.nav = nav;
    }


    /**
     * Returns the speed of the transporter
     * @return double
     */
    public double getCurrentSpeed(){
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
    public void setCurrentSpeed(double amount) {
        this.currentSpeed = amount;
    }

    /**
     * Speed up the transporter.
     * @param amount: change amount.
     */
    public void gas(double amount) {
        amount = clamp(amount, 0,1);
        incrementSpeed(amount);
    }

    /**
     * Speed down the transporter.
     * @param amount: change amount.
     */
    public void brake(double amount) {
        amount = clamp(amount, 0, 1);
        decrementSpeed(amount);
    }

    /**
     * Clamps a value between two points.
     * @param val: value to be clamped.
     * @param min: minimum clamp value.
     * @param max: maximum clamp value.
     * @return double
     */
    public static double clamp(double val, double min, double max) {
        if (val > max)
            val = max;
        else if (val < min)
            val = min;
        return val;
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
