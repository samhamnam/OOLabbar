package cars;

import java.awt.*;
import java.util.Deque;

/**
 * A haling truck by General Motors
 */
public class GeneralMotors extends Truck {
    private final Loader<Car> cars = new Loader<>(false); // Where picked up cars are stored

    /**
     * Returns a deque of all cars transported.
     * @return Stack<cars.Car>
     */
    public Deque<Car> getCars(){
        return cars.getCars();
    }

    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
    public int getMaxAngle() {
        return 70;
    }
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
    public int getMinAngle() {
        return 0;
    }
    /**
     * Returns how much the truck bed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
    public double getPickUpIncrement(){
        return getMaxAngle();
    }


    /**
     * Returns a cars.GeneralMotors with default options.
     */
     public GeneralMotors() {
        super(2,Color.blue,500,"src.cars.GeneralMotors",new Dir4Navigation());
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav: The cars.Navigation instance.
     */
     public GeneralMotors(Navigation nav) {
        super(2,Color.blue,500,"src.cars.GeneralMotors",nav);
    }

    /**
     * Returns speedFactor
     * @return double
     */
    @Override
    double speedFactor() {
        return getEnginePower() * 0.01;
    }

    /**
     * Adds a car to the pickup truck, if it is close enough and the truck bed is down.
     * @param car: the car in question.
     * @return boolean
     */
    public boolean pickUpCar(Car car){
        if(car != this){
            if (getTruckbedAngle() == getMaxAngle()) {
                double distance = Math.sqrt(
                        Math.pow(car.nav.getPosition().x - nav.getPosition().x,2) +
                                Math.pow(car.nav.getPosition().y - nav.getPosition().y,2)
                );

                if(distance < 10) {
                    cars.load(car);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes a car from the pickup truck.
     * @return cars.Car
     */
    public Car dropOffCar(){
        if (getTruckbedAngle() == getMaxAngle() && cars.getCars().size() != 0)
            return cars.unload();
        return null;
    }

    /**
     * Move the car in the current direction, and all cars currently being moved.
     */
    @Override
    public void move() {
        super.move();
        for(Car car : cars.getCars()){
            car.nav.setPosition(nav.getPosition());
        }
    }
}
