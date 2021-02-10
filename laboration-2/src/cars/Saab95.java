package cars;

import java.awt.*;

/**
 * A cars.Saab95 car with turbo
 */
public class Saab95 extends Car {
    private boolean turboOn; // The Saabs fancy turbo mode.

    /**
     * Get the turbo status.
     * @return boolean
     */
    public boolean getTurboOn(){
        return turboOn;
    }

    /**
     * Returns a cars.Saab95 with default options.
     */
    public Saab95() {
        super(2,Color.red,125,"src.cars.Saab95");
        turboOn = false;
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav The cars.Navigation instance.
     */
    public Saab95(Navigation nav) {
        super(2,Color.red,125,"src.cars.Saab95",nav);
	    turboOn = false;
    }

    /**
     * Turns on turbo
     */
    public void setTurboOn(){
	    turboOn = true;
    }
    /**
     * Turns off turbo
     */
    public void setTurboOff(){
	    turboOn = false;
    }
    /**
     * Returns speedFactor
     * @return double
     */
    double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
