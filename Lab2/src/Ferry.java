/**
 * A ferry that is able to hold and transport cars.
 */
 public class Ferry extends Transporter {
    private final Loader<Car> cars = new Loader<>(true);

    Ferry() {
        super(new Dir4Navigation());
     }

    /**
     * Creates a ferry with the specified Navigation.
     * @param nav The navigation instance.
     */
     Ferry(Navigation nav) {
        super(nav);
    }

    /**
     * Returns the speedFactor.
     * @return double
     */
    @Override
    double speedFactor() {
        return 10;
    }

    /**
     * Load a car onto the ferry.
     * @param car: The car to be loaded.
     */
     void load(Car car) {
        cars.load(car);
    }

    /**
     * Unload a car from the ferry.
     */
     Car unload() {
        return cars.unload();
    }

    /**
     * Move the ferry in the current direction, and all cargo currently being moved.
     */
    @Override
    public void move() {
        super.move();
        for(Car car : cars.getCars()){
            car.nav.setPosition(nav.getPosition());
        }
    }
}
