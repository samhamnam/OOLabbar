import java.awt.geom.Point2D;

public class Ferry extends Transporter {
    private final CarLoader<Car> cars = new CarLoader<>(true);

    Ferry(Navigation nav) {
        super(nav);
    }

//    @Override
//    public void turnLeft() {
//
//    }
//
//    @Override
//    public void turnRight() {
//
//    }

    @Override
    double speedFactor() {
        return 0;
    }

    /**
     * Load a car onto the ferry.
     * @param car: The car to be loaded.
     */
    public void load(Car car) {
        cars.load(car);
    }

    /**
     * Unload a car from the ferry.
     */
    public void unload() {
        cars.unload();
    }
}
