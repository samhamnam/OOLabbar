package cars;

import java.awt.*;

/**
 * A construction truck.
 */
public class Scania extends Truck {
    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
    public int getMaxAngle() { return 70; }
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
    public int getMinAngle() { return 0; }
    /**
     * Returns how much the truck bed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
    public double getPickUpIncrement() {return 10;}

    /**
     * Returns a cars.Scania with default options.
     */
    public Scania() {
        super(2, Color.red,500,"src.cars.Scania");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav: The cars.Navigation instance handling how it moves.
     */
    public Scania(Navigation nav) {
        super(2,Color.blue,500,"src.cars.Scania",nav);
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
