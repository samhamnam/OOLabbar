package controller;

import java.awt.*;
import java.util.ArrayList;

public interface IView<Paintable>{
    void addPaintables(ArrayList<Paintable> paintables);
    Paintable getPanel();
    void paint();
}
