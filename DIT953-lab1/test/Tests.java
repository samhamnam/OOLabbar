package test;

import org.junit.Before;
import org.junit.Test;
import src.Car;
import src.Saab95;
import src.Volvo240;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertTrue;


public class Tests {
    static final Random rnd = new Random();
    ArrayList<Car> cars = new ArrayList<>();

    @Before
    public void start() {
        cars.add(new Volvo240(randomDir(),randomPoint()));
        cars.add(new Saab95(randomDir(),randomPoint()));
    }
    Point2D.Double randomPoint(){
        return new Point2D.Double(rnd.nextDouble()+ rnd.nextInt(),rnd.nextDouble()+ rnd.nextInt());
    }
    Car.Dir randomDir(){
        return Car.Dir.values()[rnd.nextInt(4)];
    }


    @Test
    public void canTurnLeft() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp = tmp && canTurnAllDirections(car, true);
        }
        System.out.println(tmp);
        assertTrue(tmp);
    }

    @Test
    public void canTurnRight() {
        boolean tmp = true;
        for (Car car : cars) {
            tmp = tmp && canTurnAllDirections(car, false);
        }
        System.out.println(tmp);
        assertTrue(tmp);
    }

    boolean canTurnAllDirections(Car car, boolean turnLeft) {
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            Car.Dir origDir = car.getDirection();

            if (turnLeft) car.turnLeft();
            else car.turnRight();

            Car.Dir newDir = car.getDirection();

            int turned = origDir.ordinal() - newDir.ordinal();

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
        for(Car car : cars) {
            car.stopEngine();
            car.startEngine();
            correct = correct && car.getCurrentSpeed() > 0;
        }
        assertTrue(correct);
    }

    @Test
    public void testSaab95Turbo(){
        Saab95 saab = new Saab95();
        saab.setTurboOn();
        boolean correct = saab.getTurboOn();
        saab.setTurboOff();
        correct = correct && !saab.getTurboOn();
        assertTrue(correct);
    }

    @Test
    public void testConstructorAndGetters() {
        Car.Dir dir = Car.Dir.RIGHT;
        Point2D.Double pos = new Point2D.Double(0,0);

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

        assertTrue(doors && enginePower && currentSpeed && color && direction && position && colorApplied);
    }

    @Test
    public void gasUpCarExtremeValues() {
        for (Car car : cars) {
            car.gas(Double.MAX_VALUE);
            car.gas(Double.MIN_VALUE);
            car.gas(0);
        }
        assertTrue(true);
    }

    @Test
    public void brakeCarExtremeValues() {
        for (Car car : cars) {
            car.brake(Double.MAX_VALUE);
            car.brake(Double.MIN_VALUE);
            car.brake(0);
        }
        assertTrue(true);
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
}
