package controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import controller.CarEvent.Command;

public class CarController<Event, Paintable> implements IController<Event, Paintable> {


    ArrayList<CarEvent> events = new ArrayList<>();

    JSpinner gasSpinner = new JSpinner();
    JPanel finalPanel;
    JPanel controlPanel;
    JPanel gasPanel;



    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");




    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public ArrayList getEvents() {
        return events;
    }

    @Override
    public ArrayList getPaintables() {
        return null;
    }


    public CarController() {
        asign();
        aperence();
    }

    private void asign(){
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("breaks",Command.GAS));
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("breaks",Command.BRAKE));
            }
        });

        turboOnButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("turbo on",Command.TURBO_ON));
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("turbo off",Command.TURBO_OFF));
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("turbo off",Command.LOWER_BED));
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("turbo off",Command.LIFT_BED));
            }
        });
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                events.add(new CarEvent("turbo off",Command.LIFT_BED));
            }
        });
    }

    private void aperence(){
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);



        //controlPanel.add(gasLabel, BorderLayout.PAGE_START);
        controlPanel.add(gasSpinner, BorderLayout.PAGE_END);


        gasPanel.setLayout(new BorderLayout());
        // gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        finalPanel.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((800 / 2) + 4, 200));
        finalPanel.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(800 / 5 - 15, 200));
        finalPanel.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.white);
        stopButton.setPreferredSize(new Dimension(800 / 5 - 15, 200));
        finalPanel.add(stopButton);
    }
}
