package controller;

import cars.*;
import controller.CarEvent.Command;
import controller.abracts.*;
import util.Tuple;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarModel{
    private final ArrayList<Transporter> cars;
    private EventMatching<Command, Func> EM;


    private double speed;

    interface Func {
        void apply(double d);
    }

    public CarModel(ArrayList<Transporter> cars, double speed) {
        this.cars = cars;
        int y = 0;
        for (Transporter car: cars) {
            car.nav.setPosition(new Point2D.Double(car.nav.getPosition().getX(),y));
            y += 100;
        }
        this.speed = speed;

        assign();
    }

    public Tuple<Double,String> getFirstSpeedAndClass() {
        if(cars.size() == 0) return new Tuple<>(0.0, "I am speed.");
        return new Tuple<>( cars.get(0).getCurrentSpeed(), cars.get(0).getClass().getSimpleName());
    }

    public ArrayList<Tuple<String,Point2D>> getCarPositionsAndName() {
        ArrayList<Tuple<String,Point2D>> poss = new ArrayList<>();
        for (Transporter car : cars) {
            poss.add(new Tuple<>(car.getClass().getSimpleName(),(Point2D.Double) car.nav.getPosition().clone()));
        }
        return poss;
    }

    private void assign() {
        this.EM = new EventMatching<>(Command.values());
        EM.assign(
                Command.GAS,
                this::gas
        );
        EM.assign(
                Command.BRAKE,
                this::gas
        );
        EM.assign(
                Command.LIFT_BED,
                this::pickupUp
        );
        EM.assign(
                Command.LOWER_BED,
                this::pickupDown
        );
        EM.assign(
                Command.TURBO_OFF,
                this::turboOff
        );
        EM.assign(
                Command.TURBO_ON,
                this::turboOn
        );
        EM.assign(
                Command.GAS_SPEED,
                this::setSpeed
        );
        EM.assign(
                Command.NEW_CAR,
                this::addCar
        );
        EM.assign(
                Command.REMOVE_CAR,
                this::removeCar
        );
    }



    public CarModel update(ArrayList<CarEvent> events) {
        //TODO For this to work the cars would also need to be made immutable. But this works as proof of concept.
        CarModel newModel = new CarModel(cars,speed);
        for(CarEvent e : events){
            newModel.EM.getFunc(e.getCommand()).apply(e.getAmount());
        }

        for (Transporter car: cars) {
            car.move();

            int x = (int) Math.round(car.nav.getPosition().getX());
            int y = (int) Math.round(car.nav.getPosition().getY());

            if (x < 0 || x > 690) {
                car.nav.setPosition(new Point2D.Double(Car.clamp(x,0,690),Car.clamp(y,0,700)));
                car.turnLeft();
                car.turnLeft();
            }
        }

        return newModel;
    }

    private void gas(double amount) {
        double gas = ((double) speed) / 100;
        for (Transporter car : cars) {
            car.gas(gas);
        }
    }

    private void brake(double amount) {
        double gas = ((double) amount) / 100;
        for (Transporter car : cars) {
            car.brake(gas);
        }
    }

    private void turboOn(double amount) {
        for (Transporter car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    private void turboOff(double amount) {
        for (Transporter car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    private void pickupUp(double amount) {
        for (Transporter car : cars) {
            if (car instanceof Truck) {
                ((Truck) car).raisePickup();
            }
        }
    }

    private void pickupDown(double amount) {
        for (Transporter car : cars) {
            if (car instanceof Truck) {
                ((Truck) car).lowerPickup();
            }
        }
    }

    private void setSpeed(double amount) {
        speed = amount;
    }

    private void addCar(double amount){
        if(cars.size() < 10)
            cars.add(new Volvo240());
    }

    private void removeCar(double amount){
        if(cars.size() > 0)
            cars.remove(cars.size() - 1);
    }

    void hej(double a){
        turboOn(a);
        gas(a);
    }
}
