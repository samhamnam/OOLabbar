package controller;

import cars.Transporter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarView<Paintable extends JComponent> implements IView<Paintable> {
    private final JPanel panel = new JPanel(new GridLayout(3,1));
    @Override
    public void addPaintables(ArrayList<Paintable> paintables) {
        for(Paintable p : paintables){
            panel.add(p);
        }
    }

    @Override
    public Paintable getPanel() {
        return (Paintable) panel;
    }

    @Override
    public void paint() {

    }
}
