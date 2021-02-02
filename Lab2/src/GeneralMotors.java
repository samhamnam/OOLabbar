import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Stack;

public class GeneralMotors extends Truck {
    Stack<Car> cars = new Stack<>();
    static final int maxAngle = 70;
    static final int minAngle = 0;

    /**
     * Returns a GeneralMotors with default options.
     * @return GeneralMotors
     */
    public GeneralMotors() {
        super(2,Color.blue,500,"src.GeneralMotors",maxAngle,minAngle,maxAngle,Car.Dir.LEFT,new Point2D.Double(0,0));
    }

    /**
     * Returns a car with the specified position and direction.
     * @param dir: The direction to spawn in.
     * @param pos: Floating point position.
     * @return GeneralMotors
     */
    public GeneralMotors(Car.Dir dir, Point2D.Double pos) {
        super(2,Color.blue,500,"src.GeneralMotors",10,0,70,dir,pos);
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
     * @return void
     */
    public boolean pickUpCar(Car car){
        if(car != this){
            if (getTruckbedAngle() == getMaxAngle()) {
                double distance = Math.sqrt(
                        Math.pow(car.getPosition().x - getPosition().x,2) +
                                Math.pow(car.getPosition().y - getPosition().y,2)
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
     * @return car
     */
    public Car dropOffCar(){
        if (getTruckbedAngle() == getMaxAngle() && cars.size() != 0)
            return cars.pop();
        return null;
    }

    /**
     * Move the car in the current direction, and all cars currently being moved.
     * @return void
     */
    @Override
    public void move() {
        super.move();
        for(Car car : cars){
            car.setPosition(getPosition());
        }
    }
}
