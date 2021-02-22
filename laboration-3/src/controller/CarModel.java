package controller;

import cars.Transporter;

import java.util.ArrayList;

public class CarModel implements IModel{
    private ArrayList<Transporter> cars = new ArrayList<>();
    IController[] controllers;
    IView view;

    public CarModel(IController[] controllers, IView view){
        this.controllers = controllers;
        this.view = view;
    }

    public IController[] getControllers(){
        return controllers;
    }
    public IView getView(){
        return view;
    }

    @Override
    public void update(ArrayList arrayList) {

    }

    @Override
    public ArrayList getPaintables() {
        return null;
    }
}
