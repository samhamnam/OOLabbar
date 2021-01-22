package test;

import org.junit.Before;
import org.junit.Test;
import src.Car;
import src.Saab95;
import src.Volvo240;

import static org.junit.Assert.assertTrue;


public class Tests {
    Car[] cars;

    @Before
    public void start() {
        cars = new Car[]{new Volvo240(), new Saab95()};
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
    public void turnRightAndLeftTheSameAmountOFTimes() {
        boolean tmp = true;
        Car.Dir orginalDir;
        for (int i = 0; i < 6; i++) {
            for (Car car : cars) {
                orginalDir = car.getDirection();
                for (int j = 0; j < i; j++) {
                    car.turnRight();
                }
                for (int j = 0; j < i; j++) {
                    car.turnLeft();
                }
                tmp = tmp && (orginalDir == car.getDirection());
            }
        }
        assertTrue(tmp);
    }


}
