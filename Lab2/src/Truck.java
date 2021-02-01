import java.awt.*;
import java.awt.geom.Point2D;


public abstract class Truck  extends Car{
    protected double truckbedAngle = 0; // The current angle of the truckbed.
    protected int maxAngle;
    protected int minAngle = 0;

    /**
     * Returns the truckbed angle.
     * @return double
     */
    public double getTruckbedAngle(){
        return truckbedAngle;
    }

    public Truck(int nDoors, Color color,int enginePower,String name) {
        super(nDoors,color,enginePower,name);
    }

    public Truck(int nDoors, Color color, int enginePower, String name, Car.Dir dir, Point2D.Double pos) {
        super(nDoors,color,enginePower,name, dir, pos);
    }

    /**
     * Returns the truckbed angle.
     * @return void
     */
    public void startEngine(){
        if(truckbedAngle == minAngle)
            super.startEngine();
    }


    /**
     * Raises the truckbed angle.
     * @param amount: amount to increment the angle by in degrees by.
     * @return void
     */
    public void raisePickup(double amount){
        if (currentSpeed == 0){
            truckbedAngle = clamp(truckbedAngle + amount, minAngle, maxAngle);
        }
    }
    /**
     * Lowers the truckbed angle.
     * @param amount: amount to decrement the angle by in degrees by.
     * @return void
     */
    public void lowerPickup(double amount){
        if (currentSpeed == 0){
            truckbedAngle = clamp(truckbedAngle - amount, minAngle, maxAngle);
        }
    }
}
