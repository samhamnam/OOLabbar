import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Stack;

public class GeneralMotors extends Truck {
    Stack<Car> cars = new Stack<>();

    /**
     * Returns a GeneralMotors with default options.
     * @return GeneralMotors
     */
    public GeneralMotors() {
        super(2, Color.red,500,"src.GeneralMotors");
        maxAngle = 70;
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return GeneralMotors
     */
    public GeneralMotors(Car.Dir dir, Point2D.Double pos) {
        super(2,Color.blue,500,"src.GeneralMotors",dir,pos);
        maxAngle = 70;
    }

    /**
     * Returns speedFactor
     * @return double
     */
    @Override
    double speedFactor() {
        return enginePower * 0.01;
    }

    /**
     * Raises the truckbed.
     * @param amount: amount to increment the angle by in degrees by.
     * @return void
     */
    public void raisePickup(double amount){
        if(currentSpeed == 0)
            truckbedAngle = maxAngle;
    }

    /**
     * Lowers the truckbed.
     * @param amount: amount to decrement the angle by in degrees by.
     * @return void
     */
    public void lowerPickup(double amount){
        if(currentSpeed == 0)
            truckbedAngle = minAngle;
    }

    /**
     * Adds a car to the pickup truck, if its close enough and the truckbed is down.
     * @param car: the car in question.
     * @return void
     */
    public void pickUpCar(Car car){
        if(car != this){
            if (truckbedAngle == maxAngle) {
                double distance = Math.sqrt(
                        Math.pow(car.position.x - position.x,2) +
                                Math.pow(car.position.y - position.y,2)
                );

                if(distance < 10) {
                    cars.push(car);
                }
            }
        }
    }

    /**
     * Removes a car from the pickup truck.
     * @return car
     */
    // TODO kanske inte ska returnera?
    public Car dropOffCar(){
        if (truckbedAngle == maxAngle)
            return cars.pop();
        return null;
    }

    /**
     * Move the car in the current direction, and all cars currently being moved.
     * @return void
     */
    @Override
    public void move() {
        switch(direction) {
            case UP: position.y += speedFactor(); break;
            case DOWN: position.y -= speedFactor(); break;
            case LEFT: position.x -= speedFactor(); break;
            case RIGHT: position.x += speedFactor(); break;
        }
        for(Car car : cars){
            car.position = position;
        }
    }
}
