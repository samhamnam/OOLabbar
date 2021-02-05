import java.util.ArrayList;

public class Workshop <T extends Car>{
//    private final ArrayList<T> cars; // The cars stored in the workshop.
    private final CarLoader<T> cars = new CarLoader<>(true);
    private final int limit; // The max amount of cars stored.

    /**
     * Returns the number of cars in the workshop.
     * @return int
     */
    public int carAmount(){
        return cars.getCars().size();
    }

    /**
     * A workshop containing cars.
     * @param limit: the maximum amount of cars it can contain.
     */
    public Workshop(int limit) {
        this.limit = limit;
    }

    /**
     * Adds a car to the workshop as long as it does not contain too many.
     * @param car: the car in question.
     */
    public void addCar(T car){
        if(cars.getCars().size() < limit)
            cars.load(car);
    }

    /**
     * Returns the car at the specified index.
     * @param index: the car to return.
     * @return Car
     */
    public T getCar(int index) {
        return cars.remove(index);
    }


}
