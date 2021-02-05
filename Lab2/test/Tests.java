import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class Tests {
    static final Random rnd = new Random();
    ArrayList<Transporter> allTransports = new ArrayList<>();
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Truck> trucks = new ArrayList<>();
    ArrayList<Navigation> navs = new ArrayList<>();
    ArrayList<Ferry> ferries = new ArrayList<>();

    void addTrucks(ArrayList<Navigation> arg_navs) {
        for (Navigation nav : arg_navs) {
            ArrayList<Truck> tmp = new ArrayList<>();
            tmp.add(new Scania(nav));
            tmp.add(new GeneralMotors(nav));
            trucks.addAll(tmp);
        }
        cars.addAll(trucks);
    }

    void addCars(ArrayList<Navigation> arg_navs) {
        addTrucks( arg_navs);
        for (Navigation nav : arg_navs) {
            ArrayList<Car> tmp = new ArrayList<>();
            tmp.add(new Volvo240(nav));
            tmp.add(new Saab95(nav));
            cars.addAll(tmp);
        }
        allTransports.addAll(cars);
    }
    void addFerry(ArrayList<Navigation> arg_navs){
       for(Navigation nav : arg_navs){
           ArrayList<Ferry> tmp = new ArrayList<>();
           tmp.add(new Ferry(nav));
           ferries.addAll(tmp);
       }
       allTransports.addAll(ferries);
    }

    void addTransport(ArrayList<Navigation> arg_navs){
        addFerry(arg_navs);
        addCars(arg_navs);
    }
    void addNavs(){
        navs.add(new Dir4Navigation(randomPoint(),randomDir()));
    }

    @Before
    public void start() {
        addNavs();
        addTransport(navs);
    }

    Point2D.Double randomPoint() {
        return new Point2D.Double(rnd.nextDouble() + rnd.nextInt(), rnd.nextDouble() + rnd.nextInt());
    }

    double randomDir() {
        return (rnd.nextDouble() + rnd.nextInt()) % 2;
    }

/*
    @Test
    public void canTurnLeft() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp &= canTurnAllDirections(car, true);
        }
        assertTrue(tmp);
    }

    @Test
    public void canTurnRight() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp &= canTurnAllDirections(car, false);
        }
        assertTrue(tmp);
    }

 */

/*
    boolean canTurnAllDirections(Car car, boolean turnLeft) {
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            double origDir =car.nav.getDirection();

            if (turnLeft) car.turnLeft();
            else car.turnRight();

            double newDir = (car.nav.getDirection());
        }
        return result;
    }

 */

    @Test
    public void turnRightAndLeftTheSameAmountOfTimes() {
        boolean tmp = true;
        double originalDir;
        for (int i = 0; i < 6; i++) {
            for (Car car : cars) {
                originalDir = car.nav.getDirection();
                for (int j = 0; j < i; j++) {
                    car.turnRight();
                }
                for (int j = 0; j < i; j++) {
                    car.turnLeft();
                }
                tmp = tmp && (originalDir == car.nav.getDirection());
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
        Navigation nav1 = new Dir4Navigation(randomPoint(),randomDir());
        Navigation nav2 = new Dir4Navigation(randomPoint(),randomDir());


        Car v1 = new Volvo240(nav1);
        Car s1 = new Saab95(nav2);

        Car v2 = new Volvo240(nav1);
        Car s2 = new Saab95(nav2);

        boolean doors = v1.getNrDoors() == v2.getNrDoors() && s1.getNrDoors() == s2.getNrDoors();
        boolean enginePower = v1.getEnginePower() == v2.getEnginePower() && s1.getEnginePower() == s2.getEnginePower();
        boolean currentSpeed = v1.getCurrentSpeed() == v2.getCurrentSpeed() && s1.getCurrentSpeed() == s2.getCurrentSpeed();
        boolean color = v1.getColor() == v2.getColor() && s1.getColor() == s2.getColor();
        boolean direction = v1.nav.getDirection() == v2.nav.getDirection() && s1.nav.getDirection() == s2.nav.getDirection();
        boolean position = v1.nav.getPosition().equals(v2.nav.getPosition()) && s1.nav.getPosition().equals(s2.nav.getPosition());

        Color testColor = Color.GREEN;
        s1.setColor(testColor);
        v1.setColor(testColor);
        boolean colorApplied = s1.getColor() == testColor && v1.getColor() == testColor;

        int newNrDoors = 20;
        int newEnginePower = 1000;
        String newModelName = "test";
        double newDir = 1 / 2.0;
        s1.setNrDoors(newNrDoors);
        s1.setEnginePower(newEnginePower);
        s1.setModelName(newModelName);
        s1.nav.setDirection(newDir);

        boolean nrDoorsApplied = newNrDoors == s1.getNrDoors();
        boolean enginePwrApplied = newEnginePower == s1.getEnginePower();
        boolean modelNameApplied = newModelName.equals(s1.getModelName());
        boolean directionApplied = newDir == s1.nav.getDirection();


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
            Point2D.Double origin = car.nav.getPosition();
            for (int i = 0; i < 4; i++) {
                car.turnLeft();
                car.move();
            }
            tmp = tmp && origin.equals(car.nav.getPosition());
        }
        assertTrue(tmp);
    }

    @Test
    public void moveInACircleRightToOrigin() {
        boolean tmp = true;
        for (Car car : cars) {
            Point2D.Double origin = car.nav.getPosition();
            for (int i = 0; i < 4; i++) {
                car.turnRight();
                car.move();
            }
            tmp = tmp && origin.equals(car.nav.getPosition());
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
        GeneralMotors gm = new GeneralMotors(new Dir4Navigation(new Point2D.Double(x,y),randomDir()));
        gm.raisePickup();
        boolean tmp = true;
        for (Car car : cars) {
            car.nav.setPosition(new Point2D.Double(x, y));
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
    public void generalMotorsPickUpCarFail() {
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();
        Navigation nav = new Dir4Navigation(randomPoint(),randomDir());

        Car car = new Volvo240(nav);
        assertFalse(gm.pickUpCar(car));
    }

    @Test
    public void generalMotorsMove() {
        GeneralMotors gm = new GeneralMotors();
        gm.raisePickup();
        gm.pickUpCar(new Volvo240());
        gm.lowerPickup();
        gm.startEngine();
        gm.move();

        Point2D.Double pos = gm.nav.getPosition();
        boolean tmp = true;
        for (Car car : gm.getCars()) {
            tmp &= car.nav.getPosition() == pos;
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
    public void workshopCarsInRightOrder() {
        boolean tmp = true;
        Workshop<Car> ws = new Workshop(cars.size());
        for (Car car : cars) {
            ws.addCar(car);
        }
        for (int i = cars.size() - 1; i >= 0; i--) {
            tmp &= cars.get(i) == ws.getCar(ws.carAmount() - 1);
        }
        assertTrue(tmp);
    }

    @Test
    public void scaniaConstructorGetterSetter() {
        Scania s = new Scania();
        s.getMaxAngle();
        s.getMinAngle();
        s.getPickUpIncrement();

    }

    @Test
    public void generalConstructorGetterSetter() {
        GeneralMotors m = new GeneralMotors();
        m.getMaxAngle();
        m.getMinAngle();
        m.getPickUpIncrement();
    }
}
