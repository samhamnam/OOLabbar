import java.awt.*;
import java.awt.geom.Point2D;

/**
 * A construction truck.
 */
public class Scania extends Truck {
    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
     int getMaxAngle(){return 70;}
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
     int getMinAngle(){return 0;}
    /**
     * Returns how much the truck bed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
     double getPickUpIncrement() {return 10;}

    /**
     * Returns a Scania with default options.
     */
    Scania() {
        super(2, Color.red,500,"src.Scania");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav: The Navigation instance handling how it moves.
     */
    Scania(Navigation nav) {
        super(2,Color.blue,500,"src.Scania",nav);
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
