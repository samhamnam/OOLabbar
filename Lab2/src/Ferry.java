 public class Ferry extends Transporter {
    private final CarLoader<Car> cars = new CarLoader<>(true);

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
}
