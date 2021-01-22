package src;

import java.awt.*;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4,Color.black,100, "src.Volvo240");
    }

    public Volvo240(Car.Dir dir, double[] pos) {
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
     * Return the speed factor.
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
    private void incrementSpeed(double amount) {
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    /**
     * Decrement the speed.
     * @param amount: amount to decrement by.
     * @return void
     */
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    /**
     * Speed up the car.
     * @param amount: change amount.
     * @return void
     */
    public void gas(double amount) {
        incrementSpeed(amount);
    }
    /**
     * Speed down the car.
     * @param amount: change amount.
     * @return void
     */
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}
