package controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*Application<Integer, JComponent> app = new Application<>(
            new IModel[]{new CarModel()}
        );*/

        JPanel drawPanel = new JPanel();
        drawPanel.setBackground(Color.green);
        drawPanel.setPreferredSize(new Dimension(800,400));

        JPanel drawPanel2 = new JPanel();
        drawPanel2.setBackground(Color.red);
        drawPanel2.setPreferredSize(new Dimension(800,400));

        JFrame window = new JFrame();
        window.setTitle("Car sim");
        window.setPreferredSize(new Dimension(800,800));
        window.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        var l = new JComponent[]{
                drawPanel,drawPanel2
        };

        for(JComponent part : l){
            window.add(part);
        }
        window.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*while(true){
            drawPanel.repaint();
        }*/

        //app.run();
    }
}
