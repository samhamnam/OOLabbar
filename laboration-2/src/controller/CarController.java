package controller;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import cars.*;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        Scania scania = new Scania();
        Saab95 saab95 = new Saab95();
        scania.nav.setPosition(new Point2D.Double(0,100));
        saab95.nav.setPosition(new Point2D.Double(0,200));
        cc.cars.add(scania);
        cc.cars.add(saab95);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.nav.getPosition().getX());
                int y = (int) Math.round(car.nav.getPosition().getY());

                if (x < 0 || x > 690 || y < 0 || y > 700) {
                    car.nav.setPosition(new Point2D.Double(Car.clamp(x,0,690),Car.clamp(y,0,700)));
                    car.turnLeft();
                    car.turnLeft();
                    car.stopEngine();
                    car.startEngine();
                }

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake (int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(gas);
        }
    }


    void turboOn(){
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95)car).setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Car car : cars) {
            if(car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void pickupUp(){
        for (Car car : cars) {
            if(car instanceof Truck){
                ((Truck) car).raisePickup();
            }
        }
    }

    void pickupDown(){
        for (Car car : cars) {
            if(car instanceof Truck){
                ((Truck) car).lowerPickup();
            }
        }
    }
}
