import java.util.ArrayDeque;
import java.util.Deque;

 public class CarLoader <T extends Car> {
    private Deque<T> cars = new ArrayDeque<>(); // A car container
    private final boolean fifo; // Whether to use First-In-First-Out method

    /**
     * Creates a car loader container.
     * @param fifo: First-in-first-out method
     */
    CarLoader(boolean fifo) {
        this.fifo = fifo;
    }

    /**
     * Get the loaded cars.
     * @return Deque<T>
     */
     Deque<T> getCars() {
        return cars;
    }

    /**
     * Load a car into the deque.
     * @param car
     */
     void load(T car) {
        if (fifo)
            cars.add(car);
        else
            cars.push(car);
    }

    /**
     * Unload a car from the deque.
     * @return T
     */
    T unload() {
        return cars.pop(); // poll() to return null if empty
    }
}
