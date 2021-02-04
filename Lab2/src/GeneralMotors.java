import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Stack;

public class GeneralMotors extends Truck {
    private final Stack<Car> cars = new Stack<>(); // Where picked up cars are stored

    /**
     * Returns the amount of cars stored.
     * @return int
     */
    public int getCarAmount() {
        return cars.size();
    }

    /**
     * Returns a stack of all cars transported.
     * @return Stack<Car>
     */
    public Stack<Car> getCars(){
        return cars;
    }

    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
    int getMaxAngle() {
        return 70;
    }
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
    int getMinAngle() {
        return 0;
    }
    /**
     * Returns how much the truck bed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
    double getPickUpIncrement(){
        return getMaxAngle();
    }


    /**
     * Returns a GeneralMotors with default options.
     */
    public GeneralMotors() {
        super(2,Color.blue,500,"src.GeneralMotors",new Dir4Navigation());
    }

    /**
     * Returns a car with the specified position and direction.
     * @param nav: The Navigation instance.
     */
    public GeneralMotors(Navigation nav) {
        super(2,Color.blue,500,"src.GeneralMotors",nav);
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
     * Adds a car to the pickup truck, if its close enough and the truckbed is down.
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
                    cars.push(car);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes a car from the pickup truck.
     * @return Car
     */
    public Car dropOffCar(){
        if (getTruckbedAngle() == getMaxAngle() && cars.size() != 0)
            return cars.pop();
        return null;
    }

    /**
     * Move the car in the current direction, and all cars currently being moved.
     */
    @Override
    public void move() {
        super.move();
        for(Car car : cars){
            car.nav.setPosition(nav.getPosition());
        }
    }
}
