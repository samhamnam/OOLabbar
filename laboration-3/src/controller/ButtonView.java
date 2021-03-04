package controller;

import controller.interfaces.IView;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonView implements IView<JComponent> {
    private final JPanel panel;

    JSpinner gasSpinner = new JSpinner();

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JButton addCarButton = new JButton("Add car!");
    JButton removeCarButton = new JButton("Remove car!");

    public void setAddCarButtonAction(ActionListener al){
        addCarButton.addActionListener(al);
    }
    public void setRemoveCarButtonAction(ActionListener al){
        removeCarButton.addActionListener(al);
    }

    public void setGasButtonAction(ActionListener al){
        gasButton.addActionListener(al);
    }
    public void setBrakeButtonAction(ActionListener al){
        brakeButton.addActionListener(al);
    }
    public void setTurbonOnButtonAction(ActionListener al){
        turboOnButton.addActionListener(al);
    }
    public void setTurboOffButtonAction(ActionListener al){
        turboOffButton.addActionListener(al);
    }
    public void setLifeBedButtonAction(ActionListener al){
        liftBedButton.addActionListener(al);
    }
    public void setLowerBedButtonAction(ActionListener al){
        lowerBedButton.addActionListener(al);
    }
    public void setStartButtonAction(ActionListener al){
        startButton.addActionListener(al);
    }
    public void setStopButtonAction(ActionListener al){
        stopButton.addActionListener(al);
    }
    public void setSpinnerAction(ChangeListener cl){gasSpinner.addChangeListener(cl);}

    public ButtonView(int x, int y, int width, int height, Color color, LayoutManager layout) {
        panel = new JPanel(layout);
        panel.setBounds(x,y,width,height);
        panel.setBackground(color);
        appearance();
    }

    @Override
    public JComponent getPanel() {
        return panel;
    }

    @Override
    public void update() {    }

    private void appearance() {
        JPanel controlPanel = new JPanel(new GridLayout());;
        JPanel gasPanel = new JPanel(new GridLayout());;

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

        panel.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton,6);
        controlPanel.add(removeCarButton,7);
        controlPanel.setPreferredSize(new Dimension((800 / 2) + 4, 200));
        panel.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(800 / 5 - 15, 200));
        panel.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.white);
        stopButton.setPreferredSize(new Dimension(800 / 5 - 15, 200));
        panel.add(stopButton);
    }
}
