import java.awt.*;
import java.awt.geom.Point2D;

public class Scania extends Truck {
    static final int maxAngle = 70;
    static final int minAngle = 0;
    static final int pickupSpeed = 10;

    /**
     * Returns a Scania with default options.
     * @return Scania
     */
    public Scania() {
        super(2, Color.red,500,"src.Scania", minAngle, maxAngle);
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return Scania
     */
    public Scania(Car.Dir dir, Point2D.Double pos) {
        super(2,Color.blue,500,"src.Scania",pickupSpeed,minAngle,maxAngle,dir,pos);
    }

    /**
     * Returns speedFactor
     * @return double
     */
    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }
}
