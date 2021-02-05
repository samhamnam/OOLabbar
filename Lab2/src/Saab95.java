import java.awt.*;
import java.awt.geom.Point2D;

public class Saab95 extends Car {
    private boolean turboOn; // The Saabs fancy turbo mode.

    /**
     * Get the turbo status.
     * @return boolean
     */
    boolean getTurboOn(){
        return turboOn;
    }

    /**
     * Returns a Saab95 with default options.
     */
    Saab95() {
        super(2,Color.red,125,"src.Saab95");
        turboOn = false;
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav The Navigation instance.
     */
    Saab95(Navigation nav) {
        super(2,Color.red,125,"src.Saab95",nav);
	    turboOn = false;
    }

    /**
     * Turns on turbo
     */
    void setTurboOn(){
	    turboOn = true;
    }
    /**
     * Turns off turbo
     */
    void setTurboOff(){
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
