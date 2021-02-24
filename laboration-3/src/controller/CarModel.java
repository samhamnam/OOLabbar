package controller;

import cars.Transporter;

import java.util.ArrayList;

public class CarModel implements IModel {
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

    @Override
    public IView[] getViews() {
        return new IView[0];
    }

    public IView getView(){
        return view;
    }

    public void update(ArrayList arrayList) {

    }

    public ArrayList getPaintables() {
        return null;
    }
}
