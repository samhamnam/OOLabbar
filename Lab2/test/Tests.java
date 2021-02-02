import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;


public class Tests {
    static final Random rnd = new Random();
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Truck> trucks = new ArrayList<>();

    @Before
    public void start() {
        trucks.add(new Scania(randomDir(), randomPoint()));
        trucks.add(new GeneralMotors(randomDir(), randomPoint()));

        cars.addAll(trucks);
        cars.add(new Volvo240(randomDir(), randomPoint()));
        cars.add(new Saab95(randomDir(), randomPoint()));

    }

    Point2D.Double randomPoint() {
        return new Point2D.Double(rnd.nextDouble() + rnd.nextInt(), rnd.nextDouble() + rnd.nextInt());
    }

    Car.Dir randomDir() {
        return Car.Dir.values()[rnd.nextInt(4)];
    }


    @Test
    public void canTurnLeft() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp = tmp && canTurnAllDirections(car, true);
        }
        assertTrue(tmp);
    }

    @Test
    public void canTurnRight() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp = tmp && canTurnAllDirections(car, false);
        }
        assertTrue(tmp);
    }

    boolean canTurnAllDirections(Car car, boolean turnLeft) {
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            Car.Dir origDir = car.getDirection();

            if (turnLeft) car.turnLeft();
            else car.turnRight();

            Car.Dir newDir = car.getDirection();

            switch (origDir) {
                case LEFT:
                    result = result && newDir == (turnLeft ? Car.Dir.DOWN : Car.Dir.UP);
                    break;
                case RIGHT:
                    result = result && newDir == (turnLeft ? Car.Dir.UP : Car.Dir.DOWN);
                    break;
                case UP:
                    result = result && newDir == (turnLeft ? Car.Dir.LEFT : Car.Dir.RIGHT);
                    break;
                case DOWN:
                    result = result && newDir == (turnLeft ? Car.Dir.RIGHT : Car.Dir.LEFT);
                    break;
            }
        }
        return result;
    }

    @Test
    public void turnRightAndLeftTheSameAmountOfTimes() {
        boolean tmp = true;
        Car.Dir originalDir;
        for (int i = 0; i < 6; i++) {
            for (Car car : cars) {
                originalDir = car.getDirection();
                for (int j = 0; j < i; j++) {
                    car.turnRight();
                }
                for (int j = 0; j < i; j++) {
                    car.turnLeft();
                }
                tmp = tmp && (originalDir == car.getDirection());
            }
        }
        assertTrue(tmp);
    }

    @Test
    public void testStartEngine() {
        boolean correct = true;
        for (Car car : cars) {
            car.stopEngine();
            car.startEngine();
            correct &= car.getCurrentSpeed() > 0;
        }
        assertTrue(correct);
    }

    @Test
    public void testSaab95Turbo() {
        Saab95 saab = new Saab95();
        saab.setTurboOn();
        boolean correct = saab.getTurboOn();
        saab.setTurboOff();
        correct &= !saab.getTurboOn();
        assertTrue(correct);
    }

    @Test
    public void testConstructorAndGetters() {
        Car.Dir dir = Car.Dir.RIGHT;
        Point2D.Double pos = new Point2D.Double(0, 0);

        Car v1 = new Volvo240();
        Car s1 = new Saab95();

        Car v2 = new Volvo240(dir, pos);
        Car s2 = new Saab95(dir, pos);

        boolean doors = v1.getNrDoors() == v2.getNrDoors() && s1.getNrDoors() == s2.getNrDoors();
        boolean enginePower = v1.getEnginePower() == v2.getEnginePower() && s1.getEnginePower() == s2.getEnginePower();
        boolean currentSpeed = v1.getCurrentSpeed() == v2.getCurrentSpeed() && s1.getCurrentSpeed() == s2.getCurrentSpeed();
        boolean color = v1.getColor() == v2.getColor() && s1.getColor() == s2.getColor();
        boolean direction = v1.getDirection() == v2.getDirection() && s1.getDirection() == s2.getDirection();
        boolean position = v1.getPosition().equals(v2.getPosition()) && s1.getPosition().equals(s2.getPosition());

        Color testColor = Color.GREEN;
        s1.setColor(testColor);
        v1.setColor(testColor);
        boolean colorApplied = s1.getColor() == testColor && v1.getColor() == testColor;

        int newNrDoors = 20;
        int newEnginePower = 1000;
        String newModelName = "test";
        Car.Dir newDir = Car.Dir.UP;
        s1.setNrDoors(newNrDoors);
        s1.setEnginePower(newEnginePower);
        s1.setModelName(newModelName);
        s1.setDirection(newDir);

        boolean nrDoorsApplied = newNrDoors == s1.getNrDoors();
        boolean enginePwrApplied = newEnginePower == s1.getEnginePower();
        boolean modelNameApplied = newModelName.equals(s1.getModelName());
        boolean directionApplied = newDir == s1.getDirection();


        assertTrue(
                doors && enginePower && currentSpeed &&
                        color && direction && position &&
                        colorApplied && nrDoorsApplied && enginePwrApplied &&
                        modelNameApplied && directionApplied
        );
    }

    @Test
    public void gasUpCarExtremeValues() {
        boolean correct = true;
        for (Car car : cars) {
            double speed = car.getCurrentSpeed();
            car.gas(1);
            double newSpeed = car.getCurrentSpeed();
            correct = correct && speed < newSpeed;
        }
        assertTrue(correct);
    }

    @Test
    public void brakeCarExtremeValues() {
        boolean correct = true;
        for (Car car : cars) {
            car.gas(1);
            double speed = car.getCurrentSpeed();
            car.brake(1);
            correct = correct && speed > car.getCurrentSpeed();
        }
        assertTrue(correct);
    }

    @Test
    public void moveInACircleLeftToOrigin() {
        boolean tmp = true;
        for (Car car : cars) {
            Point2D.Double origin = car.getPosition();
            for (int i = 0; i < 4; i++) {
                car.turnLeft();
                car.move();
            }
            tmp = tmp && origin.equals(car.getPosition());
        }
        assertTrue(tmp);
    }

    @Test
    public void moveInACircleRightToOrigin() {
        boolean tmp = true;
        for (Car car : cars) {
            Point2D.Double origin = car.getPosition();
            for (int i = 0; i < 4; i++) {
                car.turnRight();
                car.move();
            }
            tmp = tmp && origin.equals(car.getPosition());
        }
        assertTrue(tmp);
    }

    @Test
    public void raiseAndLowerPickupOfTruck() {
        boolean tmp = true;
        for (Truck truck : trucks) {
            double originalAngel = truck.getTruckbedAngle();
            truck.raisePickup();
            tmp &= !(originalAngel > truck.getTruckbedAngle());
        }
        assertTrue(tmp);
    }

    @Test
    public void lowerPickupOfTruck() {
        boolean correct = true;
        for (Truck truck : trucks) {
            truck.raisePickup();
            truck.lowerPickup();

            correct &= truck.getTruckbedAngle() == 0;
        }
        assertTrue(correct);
    }

    @Test
    public void GeneralMotorsPickUpCarNear() {
        GeneralMotors gm = new GeneralMotors();

    }

    @Test
    public void GeneralMotorsPickUpAllCarClose() {
        int x = rnd.nextInt();
        int y = rnd.nextInt();
        GeneralMotors gm = new GeneralMotors(randomDir(), new Point2D.Double(x, y));
        gm.raisePickup();
        boolean tmp = true;
        for (Car car : cars) {
            car.setPosition(new Point2D.Double(x, y));
            tmp &= gm.pickUpCar(car);

        }
        assertTrue(tmp);
    }

    @Test
    public void generalMotorsDropCar() {
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();

        Car car = new Volvo240();
        gm.pickUpCar(car);
        Car droppedCar = gm.dropOffCar();

        assertEquals(car, droppedCar);
    }

    @Test
    public void generalMotorsDropCarFail() {
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();

        Car car = new Volvo240();
        gm.pickUpCar(car);
        gm.lowerPickup();

        assertNull(gm.dropOffCar());
    }

    @Test
    public void generalMotorsDropCarFailEmpty() {
        GeneralMotors gm = new GeneralMotors();
        assertNull(gm.dropOffCar());
    }

    @Test
    public void generalMotorsPickUpCarFail(){
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();

        Car car = new Volvo240(Car.Dir.LEFT, new Point2D.Double(11,11));
        assertFalse(gm.pickUpCar(car));
    }

    @Test
    public void generalMotorsMove(){
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();
        gm.pickUpCar(new Volvo240());
        gm.lowerPickup();
        gm.startEngine();
        gm.move();

        Point2D.Double pos = gm.getPosition();
        boolean tmp = true;
        for(Car car : gm.cars){
            tmp &= car.getPosition() == pos;
        }

        assertTrue(tmp);
    }

    @Test
    public void noCarLostInWorkshop() {
        Workshop<Car> ws = new Workshop(cars.size());
        for (Car car : cars) {
            ws.addCar(car);
        }
        assertEquals(ws.carAmount(), cars.size());
    }
    
    @Test
    public void workshopCarsInRightOrder(){
        boolean tmp = true;
        Workshop<Car> ws = new Workshop(cars.size());
        for (Car car : cars) {
            ws.addCar(car);
        }
        for(int i = cars.size() - 1; i >= 0; i--){
            tmp &= cars.get(i) == ws.getCar(ws.carAmount()-1);
        }
        assertTrue(tmp);
    }
    
    @Test
    public void scaniaConstructorGetterSetter(){
        Scania s = new Scania();
        s.getMaxAngle();
        s.getMinAngle();
        s.getPickUpIncrement();

    }
    public void generalConstructorGetterSetter(){
        GeneralMotors m = new GeneralMotors();
    }

}
