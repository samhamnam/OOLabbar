package src;

import java.awt.*;

public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected Dir direction;
    protected int[] position;

    public enum Dir{
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public Car(int nrDoors, Color color, int enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.direction = Dir.RIGHT;
        this.position  = new int[]{0,0};
        stopEngine();
    }

    public Car(int nrDoors, Color color, int enginePower, String modelName, Dir direction, int[] position) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.direction = direction;
        this.position = position;
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }
    public Dir getDirection() {
        return direction;
    }
    public int[] getPosition() {
        return position;
    }


    public void stopEngine(){
        currentSpeed = 0;
    }


    // TODO fix this method according to lab pm
    abstract public void gas(double amount);

    // TODO fix this method according to lab pm
    abstract public void brake(double amount);



    abstract void startEngine();
    abstract double speedFactor();

    public void move() {
        //pos
    }
    public void turnLeft() {

    }
    public void turnRight() {

    }
}
