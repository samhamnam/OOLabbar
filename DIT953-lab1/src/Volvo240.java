package src;

import java.awt.*;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4,Color.black,100, "src.Volvo240");
    }

    public Volvo240(Car.Dir dir, int[] pos) {
        super(4,Color.black,100, "src.Volvo240", dir, pos);
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }
    
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }

    private void incrementSpeed(double amount) {
	    currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    public void gas(double amount) {
        incrementSpeed(amount);
    }
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}
