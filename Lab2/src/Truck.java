import java.awt.*;
import java.awt.geom.Point2D;


public abstract class Truck extends Car {
    private double truckbedAngle; // The current angle of the truckbed.
    private int maxAngle;
    private int minAngle;
    private double pickUpIncrement;

    public int getMaxAngle() {
        return maxAngle;
    }
    public int getMinAngle() {
        return minAngle;
    }
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

    public Truck(int nDoors, Color color,int enginePower,String name,int maxAngle, int minAngle) {
        super(nDoors,color,enginePower,name);
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
    }

    public Truck(int nDoors, Color color, int enginePower, String name,
                 double pickincr, int maxAngle, int minAngle,Car.Dir dir, Point2D.Double pos) {
        super(nDoors,color,enginePower,name, dir, pos);
        pickUpIncrement = pickincr;
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
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
     * @return void
     */
    public void raisePickup(){
        if (getCurrentSpeed() == 0){
            truckbedAngle = clamp(truckbedAngle + pickUpIncrement, minAngle, maxAngle);
        }
    }
    /**
     * Lowers the truckbed angle.
     * @return void
     */
    public void lowerPickup(){
        if (getCurrentSpeed() == 0){
            truckbedAngle = clamp(truckbedAngle - pickUpIncrement, minAngle, maxAngle);
        }
    }


}
