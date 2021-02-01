import java.awt.*;
import java.awt.geom.Point2D;

public class Scania extends Truck {
    /**
     * Returns a Scania with default options.
     * @return Scania
     */
    public Scania() {
        super(2, Color.red,500,"src.Scania");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return Scania
     */
    public Scania(Car.Dir dir, Point2D.Double pos) {
        super(2,Color.blue,500,"src.Scania",dir,pos);
    }

    /**
     * Returns speedFactor
     * @return double
     */
    @Override
    double speedFactor() {
        return enginePower * 0.01;
    }
}
