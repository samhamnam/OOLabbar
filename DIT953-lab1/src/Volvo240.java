package src;

import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4,Color.black,100, "src.Volvo240");
    }

    public Volvo240(Car.Dir dir, Point2D.Double pos) {
        super(4,Color.black,100, "src.Volvo240", dir, pos);
    }

    /**
     * Start the engine.
     * @return void
     */
    public void startEngine(){
	    currentSpeed = 0.1;
    }

    /**
     * Returns the speed factor.
     * @return void
     */
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    /**
     * Increment the speed.
     * @param amount: amount to increment by.
     * @return void
     */
    void incrementSpeed(double amount) {
	    setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower));
    }
    /**
     * Decrement the speed.
     * @param amount: amount to decrement by.
     * @return void
     */
    void decrementSpeed(double amount) {
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }
}
