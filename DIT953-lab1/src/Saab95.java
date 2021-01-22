package src;

import java.awt.*;

public class Saab95 extends Car {
    private boolean turboOn;
    
    public Saab95(Car.Dir dir, double[] pos) {
        super(2,Color.red,125,"src.Saab95",dir,pos);
	    turboOn = false;
    }


    public Saab95() {
        super(2,Color.red,125,"src.Saab95");
        turboOn = false;
    }

    /**
     * Start the Engine
     * @return void
     */
    public void startEngine(){
	    currentSpeed = 0.1;
    }

    /**
     * Turns on turbo
     * @return void
     */
    public void setTurboOn(){
	    turboOn = true;
    }
    /**
     * Turns off turbo
     * @return void
     */
    public void setTurboOff(){
	    turboOn = false;
    }
    /**
     * Returns speedFactor
     * @return double
     */
    public double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
    /**
     * Increments the speed
     * @return void
     */
    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }
    /**
     * Decrement the speed
     * @return void
     */
    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
    /**
     * Speed up the car
     * @return void
     */
    public void gas(double amount) {
        incrementSpeed(amount);
    }
    /**
     * Speed down the car
     * @return void
     */
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}
