import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25; // The trimfactor for the volvo

    /**
     * Returns a Volvo240 with default options.
     */
    public Volvo240() {
        super(4,Color.black,100, "src.Volvo240");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir The direction to spawn in.
     * @param pos Floating point position.
     */
    public Volvo240(Car.Dir dir, Point2D.Double pos) {
        super(4,Color.black,100, "src.Volvo240", dir, pos);
    }

    /**
     * Returns the speed factor.
     */
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
