package controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import controller.CarEvent.Command;
import controller.abracts.Controller;
import controller.abracts.IEventListener;

public class CarController extends Controller {
    CarModelWrapper model;
    public CarController(ButtonView buttonView, CarModelWrapper model) {
        assign(buttonView);
        this.model = model;
        assign2();
    }
    private EventMatching<Command, CarModel.Func> EM;
    private void assign2() {
        this.EM = new EventMatching<>(Command.values());
        EM.assign(
                Command.GAS,
                model::gas
        );
        EM.assign(
                Command.BRAKE,
                model::gas
        );
        EM.assign(
                Command.LIFT_BED,
                model::pickupUp
        );
        EM.assign(
                Command.LOWER_BED,
                model::pickupDown
        );
        EM.assign(
                Command.TURBO_OFF,
                model::turboOff
        );
        EM.assign(
                Command.TURBO_ON,
                model::turboOn
        );
        EM.assign(
                Command.GAS_SPEED,
                model::setSpeed
        );
        EM.assign(
                Command.NEW_CAR,
                model::addCar
        );
        EM.assign(
                Command.REMOVE_CAR,
                model::removeCar
        );
    }
    private void assign(ButtonView buttonView) {

        buttonView.setGasButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.GAS).apply(0);
            }
        });
        buttonView.setSpinnerAction(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double d = (int) ((JSpinner) e.getSource()).getValue();
                EM.getFunc(Command.GAS_SPEED).apply(d);
            }
        });
        buttonView.setBrakeButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.BRAKE).apply(1);
            }
        });

        buttonView.setTurbonOnButtonAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.TURBO_ON).apply(0);
            }
        });

        buttonView.setTurboOffButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.TURBO_OFF).apply(0);
            }
        });

        buttonView.setLowerBedButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.LOWER_BED).apply(0);
            }
        });

        buttonView.setLifeBedButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.LIFT_BED).apply(0);
            }
        });

        buttonView.setAddCarButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.NEW_CAR).apply(0);
            }
        });

        buttonView.setRemoveCarButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EM.getFunc(Command.REMOVE_CAR).apply(0);
            }
        });
    }
}
