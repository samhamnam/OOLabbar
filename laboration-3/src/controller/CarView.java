package controller;

import cars.Transporter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class CarView implements IView<JComponent> {
    private final JPanel panel = new JPanel(null);

    public CarView(){
        panel.setPreferredSize(new Dimension(800,400));
        panel.setBackground(Color.orange);
    }

    @Override
    public void addPaintables(ArrayList<JComponent> paintables) {
        for(JComponent p : paintables){
            panel.add(p);
        }
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public JComponent getPanel() {
        return panel;
    }

    @Override
    public void paint() {

    }
}
