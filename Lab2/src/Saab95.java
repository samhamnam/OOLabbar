import java.awt.*;
import java.awt.geom.Point2D;

public class Saab95 extends Car {
    private boolean turboOn;


    /**
     * Get the turbo status.
     * @return boolean
     */
    public boolean getTurboOn(){
        return turboOn;
    }

    /**
     * Returns a Saab95 with default options.
     * @return Saab95
     */
    public Saab95() {
        super(2,Color.red,125,"src.Saab95");
        turboOn = false;
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return Saab95
     */
    public Saab95(Car.Dir dir, Point2D.Double pos) {
        super(2,Color.red,125,"src.Saab95",dir,pos);
	    turboOn = false;
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
        return getEnginePower() * 0.01 * turbo;
    }
}
