package controller;

import cars.*;
import controller.CarEvent.Command;
import controller.interfaces.*;
import util.PictureLoader;
import util.Tuple;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CarModel implements IModel,IEventListener<CarEvent> {
    private final ArrayList<Transporter> cars;
    private EventMatching<Command> EM;

    public Tuple<Double,String> getFirstSpeedAndClass() {
        return new Tuple<>(cars.get(0).getCurrentSpeed(), cars.get(0).getClass().getSimpleName());
    }

    // TODO outside of area of expertise
    public ArrayList<Tuple<BufferedImage,Point2D>> getCarPositionsAndImage(){
        ArrayList<Tuple<BufferedImage,Point2D>> poss = new ArrayList<>();
        for(Transporter car : cars){
            poss.add(new Tuple<>(PictureLoader.getImage(car),car.nav.getPosition()));
        }
        return poss;
    }

    private double speed = 0;

    public CarModel( ArrayList<Transporter> cars) {
        this.cars = cars;
        int y = 0;
        for(Transporter car: cars){
            car.nav.setPosition(new Point2D.Double(0,y));
            y+= 100;
        }

        assign();
    }

    private void assign() {
        this.EM = new EventMatching<>(Command.values());
        EM.assign(
                Command.GAS,
                this::gas
        );
        EM.assign(
                Command.BRAKE,
                this::brake
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

    @Override
    public void newEvent(CarEvent e) {
        EM.getFunc(e.getCommand()).apply(e.getAmount());
    }

    public void update() {
        for (Transporter car: cars) {
            car.move();

            int x = (int) Math.round(car.nav.getPosition().getX());
            int y = (int) Math.round(car.nav.getPosition().getY());

            if (x < 0 || x > 690 || y < 0 || y > 700) {
                car.nav.setPosition(new Point2D.Double(Car.clamp(x,0,690),Car.clamp(y,0,700)));
                car.turnLeft();
                car.turnLeft();
            }
        }
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
        System.out.println(amount);
    }

    private void addCar(double amount){
        if(cars.size() < 10)
            cars.add(new Volvo240());
    }
    private void removeCar(double amount){
        if(cars.size() > 0)
            cars.remove(cars.size() - 1);
    }
}
