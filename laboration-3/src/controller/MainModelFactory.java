package controller;

import cars.Transporter;
import controller.interfaces.*;

import java.util.ArrayList;
import java.util.HashSet;

public class MainModelFactory {

    public static <Event, Paintable> MainModel createMainModel(ArrayList<Transporter> cars,
                                                               HashSet<IModel> models,
                                                               HashSet<Controller<Event>> controllers,
                                                               HashSet<IView<Paintable>> views,
                                                               IWindow<Paintable> window
        ){
        for(IView<Paintable> view : views){
            window.add(view.getPanel());
        }
        return new MainModel(models, views,window);
    }
    /*
    public static <Event>Controller CreatController( HashSet<IEventListener<Event>> listeners,
                                                     ){

    }

     */



}
