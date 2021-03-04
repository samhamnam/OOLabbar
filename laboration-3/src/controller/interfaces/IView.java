package controller.interfaces;

import java.awt.*;
import java.util.ArrayList;

public interface IView<Paintable> {
    Paintable getPanel();
    void update();
}
