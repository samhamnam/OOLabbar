package controller;

import cars.Transporter;
import com.sun.jdi.connect.Transport;
import controller.interfaces.IView;
import util.Tuple;

import javax.swing.*;
import java.awt.*;

public class CarSpeedView implements IView<JComponent> {
    final private CarModel model;
    private JPanel panel = new JPanel(new GridLayout());
    private JLabel label = new JLabel();


    public CarSpeedView (CarModel model){
        this.model = model;
        panel.setBounds(0,400,300,100);
        panel.add(label);
        panel.setBackground(Color.cyan);
        label.setBounds(0,0,100,100);
    }

    @Override
    public JComponent getPanel() {
        return panel;
    }

    @Override
    public void update() {
        Tuple<Double,String> t = model.getFirstSpeedAndClass();
        label.setText(t.getRight() + " " + t.getLeft());
    }
}
