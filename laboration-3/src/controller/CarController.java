package controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.CarEvent.Command;

public class CarController implements IController<CarEvent, JComponent> {
    ArrayList<CarEvent> events = new ArrayList<>();

    JSpinner gasSpinner = new JSpinner();
    JPanel finalPanel = new JPanel(new GridLayout());
    JPanel controlPanel = new JPanel(new GridLayout());;
    JPanel gasPanel = new JPanel(new GridLayout());;

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
    public ArrayList<CarEvent> getEvents() {
        return events;
    }

    @Override
    public ArrayList<JComponent> getPaintables() {
        ArrayList<JComponent> paintables = new ArrayList<>();
        paintables.add(finalPanel);
        return paintables;
    }

    public CarController() {
        appearance();
        assign();
    }

    private void assign() {
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("breaks",Command.GAS));
            }
        });
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                double d = (int) ((JSpinner)e.getSource()).getValue();
                events.add(new CarEvent("the acceleration",Command.GAS_SPEED, d));
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
                events.add(new CarEvent("lower bed",Command.LOWER_BED));
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                events.add(new CarEvent("lift bed",Command.LIFT_BED));
            }
        });
    }

    private void appearance() {
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
