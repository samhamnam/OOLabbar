import java.util.Deque;

public class CarLoader <T extends Car> {
    private Deque<T> cars; // A car container
    private final boolean fifo; // Whether to use First-In-First-Out method

    /**
     * Creates a car loader container.
     * @param fifo: First-in-first-out method
     */
    public CarLoader(boolean fifo) {
        this.fifo = fifo;
    }

    /**
     * Get the loaded cars.
     * @return Deque<T>
     */
    public Deque<T> getCars() {
        return cars;
    }

    /**
     * Load a car into the deque.
     * @param car
     */
    public void load(T car) {
        if (fifo)
            cars.add(car);
        else
            cars.push(car);
    }

    /**
     * Unload a car from the deque.
     * @return T
     */
    public T unload() {
        return cars.pop(); // poll() to return null if empty
    }
}
