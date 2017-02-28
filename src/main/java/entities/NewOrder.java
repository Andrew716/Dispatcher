package entities;

/**
 * Created by phoenix on 27.02.17.
 */
public class NewOrder{

    private double newLatitude;
    private double newLongitude;
    private double newAltitude;

    public NewOrder(double newLatitude, double newLongitude, double newAltitude) {
        this.newLatitude = newLatitude;
        this.newLongitude = newLongitude;
        this.newAltitude = newAltitude;
    }

    public double getNewLatitude() {
        return newLatitude;
    }

    public double getNewLongitude() {
        return newLongitude;
    }

    public double getNewAltitude() {
        return newAltitude;
    }
}
