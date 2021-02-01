import java.awt.*;
import java.awt.geom.Point2D;

public class Volvo240 extends Car {
    private final static double trimFactor = 1.25;

    /**
     * Returns a Volvo240 with default options.
     * @return Volvo240
     */
    public Volvo240() {
        super(4,Color.black,100, "src.Volvo240");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return Volvo240
     */
    public Volvo240(Car.Dir dir, Point2D.Double pos) {
        super(4,Color.black,100, "src.Volvo240", dir, pos);
    }

    /**
     * Returns the speed factor.
     * @return void
     */
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
