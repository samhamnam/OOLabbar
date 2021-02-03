import java.util.ArrayList;

public class Workshop <T extends Car>{
    private final ArrayList<T> cars; // The cars stored in the workshop.
    private final int limit; // The max amount of cars stored.

    /**
     * Returns the amount of cars in the workshop.
     * @return int
     */
    public int carAmount(){
        return cars.size();
    }

    /**
     * A workshop containing cars.
     * @param limit: the maximum amount of cars it can contain.
     */
    public Workshop(int limit) {
        cars = new ArrayList<>();
        this.limit = limit;
    }

    /**
     * Adds a car to the workshop as long as it doesn't contain to many.
     * @param car: the car in question.
     */
    public void addCar(T car){
        if(cars.size() < limit)
            cars.add(car);
    }

    /**
     * Returns the car at the specified index.
     * @param index: the car to return.
     * @return Car
     */
    public T getCar(int index){
        return cars.remove(index);
    }


}
