import java.awt.*;
import java.awt.geom.Point2D;


public abstract class Truck extends Car {
    private double truckbedAngle; // The current angle of the truckbed.
    private final int maxAngle; // The maximum angle for the truckbed
    private final int minAngle; // The minimum angle for the truckbed
    private double pickUpIncrement;

    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
    public int getMaxAngle() {
        return maxAngle;
    }
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
    public int getMinAngle() {
        return minAngle;
    }
    /**
     * Returns how much the truckbed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
    public double getPickUpIncrement() {
        return pickUpIncrement;
    }

    /**
     * Returns the truckbed angle.
     * @return double
     */
    public double getTruckbedAngle(){
        return truckbedAngle;
    }

    /**
     * Creates a truck with the following parameters:
     * @param nDoors the amount of doors a truck should have.
     * @param color the color of the truck.
     * @param enginePower how strong the truck engine is.
     * @param name the name of the truck.
     * @param minAngle the minimum angle of the truckbed.
     * @param maxAngle the maximum angle of the truckbed.
     */
    public Truck(int nDoors, Color color,int enginePower,String name, int minAngle,int maxAngle) {
        super(nDoors,color,enginePower,name);
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
    }

    /**
     * Creates a truck with the following parameters:
     * @param nDoors the amount of doors a truck should have.
     * @param color the color of the truck.
     * @param enginePower how strong the truck engine is.
     * @param name the name of the truck.
     * @param pickincr how much the angle changes per increment.
     * @param minAngle the minimum angle of the truckbed.
     * @param maxAngle the maximum angle of the truckbed.
     * @param dir the direction the car should be created in.
     * @param pos the position the car should be created at.
     */
    public Truck(int nDoors, Color color, int enginePower, String name,
                 double pickincr, int minAngle,int maxAngle,Car.Dir dir, Point2D.Double pos) {
        super(nDoors,color,enginePower,name, dir, pos);
        pickUpIncrement = pickincr;
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
    }

    /**
     * Returns the truckbed angle.
     */
    public void startEngine(){
        if(truckbedAngle == minAngle)
            super.startEngine();
    }


    /**
     * Raises the truckbed angle.
     */
    public void raisePickup(){
        if (getCurrentSpeed() == 0){
            truckbedAngle = clamp(truckbedAngle + pickUpIncrement, minAngle, maxAngle);
        }
    }
    /**
     * Lowers the truckbed angle.
     */
    public void lowerPickup(){
        if (getCurrentSpeed() == 0){
            truckbedAngle = clamp(truckbedAngle - pickUpIncrement, minAngle, maxAngle);
        }
    }


}
