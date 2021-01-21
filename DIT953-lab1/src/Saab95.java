package src;

import java.awt.*;

public class Saab95 extends Car {
    private boolean turboOn;
    
    public Saab95(Car.Dir dir, int[] pos) {
        super(2,Color.red,125,"src.Saab95",dir,pos);
	    turboOn = false;
    }

    public Saab95() {
        super(2,Color.red,125,"src.Saab95");
        turboOn = false;
    }

    public void startEngine(){
	    currentSpeed = 0.1;
    }

    public void setTurboOn(){
	    turboOn = true;
    }
    public void setTurboOff(){
	    turboOn = false;
    }
    
    public double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

    private void incrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }
    private void decrementSpeed(double amount) {
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }

    public void gas(double amount) {
        incrementSpeed(amount);
    }
    public void brake(double amount) {
        decrementSpeed(amount);
    }
}
