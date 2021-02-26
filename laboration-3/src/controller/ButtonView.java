package controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ButtonView implements IView<JComponent>{
    private JPanel panel = new JPanel(new GridLayout(2,5));

    public ButtonView(){
        panel.setPreferredSize(new Dimension(800,300));
        panel.setBounds(0,400,800,300);
        panel.setBackground(Color.red);
    }

    @Override
    public void addPaintables(ArrayList<JComponent> jComponents) {
        for(JComponent p : jComponents){
            panel.add(p);
            System.out.println("ADDED");
        }
    }

    @Override
    public JComponent getPanel() {
        return panel;
    }

    @Override
    public void paint() {

    }
}
