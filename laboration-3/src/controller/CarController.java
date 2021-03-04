package controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import controller.CarEvent.Command;
import controller.interfaces.Controller;
import controller.interfaces.IEventListener;

public class CarController extends Controller<CarEvent> {
    private ButtonView buttonView;

    public CarController(ButtonView buttonView,HashSet<IEventListener<CarEvent>> observers) {
        super(observers);
        this.buttonView = buttonView;
        assign();
    }

    public ArrayList<JComponent> getPaintables() {
        ArrayList<JComponent> paintables = new ArrayList<>();
        paintables.add(buttonView.getPanel());
        return paintables;
    }

    private void assign() {
        buttonView.setGasButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("breaks",Command.GAS));
            }
        });
        buttonView.setSpinnerAction(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double d = (int) ((JSpinner) e.getSource()).getValue();
                notifyObservers(new CarEvent("the acceleration", Command.GAS_SPEED, d));
            }
        });
        buttonView.setBrakeButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("breaks",Command.BRAKE));
            }
        });

        buttonView.setTurbonOnButtonAction(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("turbo on",Command.TURBO_ON));
            }
        });

        buttonView.setTurboOffButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("turbo off",Command.TURBO_OFF));
            }
        });

        buttonView.setLowerBedButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("lower bed",Command.LOWER_BED));
            }
        });

        buttonView.setLifeBedButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("lift bed",Command.LIFT_BED));
            }
        });

        buttonView.setAddCarButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("add car",Command.NEW_CAR));
            }
        });

        buttonView.setRemoveCarButtonAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyObservers(new CarEvent("remove car",Command.REMOVE_CAR));
            }
        });
    }
}
