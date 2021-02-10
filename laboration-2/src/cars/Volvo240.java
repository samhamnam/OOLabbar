package cars;

import java.awt.*;

/**
 * En görgo Volvo 240
 */
public class Volvo240 extends Car {
    private final static double trimFactor = 1.25; // The trim factor for the volvo

    /**
     * Returns a cars.Volvo240 with default options.
     */
    public Volvo240() {
        super(4,Color.black,100, "src.cars.Volvo240");
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav The navigation instance.
     */
    public Volvo240(Navigation nav) {
        super(4,Color.black,100, "src.cars.Volvo240", nav);
    }

    /**
     * Returns the speed factor.
     */
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
